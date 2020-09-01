package com.neu.shop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.shop.pojo.*;
import com.neu.shop.service.GoodsService;
import com.neu.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/send")
    public String sendOrder(Model model, HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        //查询未发货订单
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIssendEqualTo(false);
        List<Order> orderList = orderService.selectOrderByExample(orderExample);
        model.addAttribute("sendOrder", orderList);

        //查询该订单中的商品
        for (int i = 0; i < orderList.size(); i++) {
            //获取订单项中的goodsid
            Order order = orderList.get(i);
            OrderItemExample orderItemExample = new OrderItemExample();
            orderItemExample.or().andOrderidEqualTo(order.getOrderid());
            List<OrderItem> orderItemList = orderService.getOrderItemByExample(orderItemExample);
            List<Integer> goodsIdList = new ArrayList<>();

            List<Goods> goodsList = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                Goods goods = goodsService.selectById(orderItem.getGoodsid());
                goods.setNum(orderItem.getNum());
                goodsList.add(goods);
            }

       
            order.setGoodsInfo(goodsList);

            //查询地址
            Address address = orderService.getAddressByKey(order.getAddressid());
            order.setAddress(address);

            orderList.set(i, order);
        }

        model.addAttribute("pageInfo", orderList);

        return "adminAllOrder";
    }

    @RequestMapping("/sendGoods")
    public String sendGoods(Integer orderid, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        Order order = new Order();
        order.setOrderid(orderid);
        order.setIssend(true);
        orderService.updateOrderByKey(order);
        return "redirect:/admin/order/send";
    }

    @RequestMapping("/receiver")
    public String receiveOrder( Model model,HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        //查询未收货订单
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIssendEqualTo(true).andIsreceiveEqualTo(false);
        List<Order> orderList = orderService.selectOrderByExample(orderExample);
        model.addAttribute("sendOrder", orderList);

        //查询该订单中的商品
        for (int i = 0; i < orderList.size(); i++) {
            //获取订单项中的goodsid
            Order order = orderList.get(i);
            OrderItemExample orderItemExample = new OrderItemExample();
            orderItemExample.or().andOrderidEqualTo(order.getOrderid());
            List<OrderItem> orderItemList = orderService.getOrderItemByExample(orderItemExample);
            List<Integer> goodsIdList = new ArrayList<>();

            List<Goods> goodsList = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                Goods goods = goodsService.selectById(orderItem.getGoodsid());
                goods.setNum(orderItem.getNum());
                goodsList.add(goods);
            }

            order.setGoodsInfo(goodsList);

            //查询地址
            Address address = orderService.getAddressByKey(order.getAddressid());
            order.setAddress(address);

            orderList.set(i, order);
        }

        model.addAttribute("pageInfo", orderList);

        return "adminOrderReceive";
    }

    @RequestMapping("/complete")
    public String completeOrder( Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        //查询已完成订单
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIssendEqualTo(true).andIsreceiveEqualTo(true).andIscompleteEqualTo(true);
        List<Order> orderList = orderService.selectOrderByExample(orderExample);
        model.addAttribute("sendOrder", orderList);

        //查询该订单中的商品
        for (int i = 0; i < orderList.size(); i++) {
            //获取订单项中的goodsid
            Order order = orderList.get(i);
            OrderItemExample orderItemExample = new OrderItemExample();
            orderItemExample.or().andOrderidEqualTo(order.getOrderid());
            List<OrderItem> orderItemList = orderService.getOrderItemByExample(orderItemExample);
            List<Integer> goodsIdList = new ArrayList<>();

            List<Goods> goodsList = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
//                goodsIdList.add(orderItem.getGoodsid());
                Goods goods = goodsService.selectById(orderItem.getGoodsid());
                goods.setNum(orderItem.getNum());
                goodsList.add(goods);
            }

            order.setGoodsInfo(goodsList);

            //查询地址
            Address address = orderService.getAddressByKey(order.getAddressid());
            order.setAddress(address);

            orderList.set(i, order);
        }

        model.addAttribute("pageInfo", orderList);
        return "adminOrderComplete";
    }
}

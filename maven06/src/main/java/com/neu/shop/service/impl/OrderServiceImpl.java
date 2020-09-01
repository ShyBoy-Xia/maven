package com.neu.shop.service.impl;

import com.neu.shop.dao.AddressMapper;
import com.neu.shop.dao.OrderItemMapper;
import com.neu.shop.dao.OrderMapper;
import com.neu.shop.pojo.*;
import com.neu.shop.service.GoodsService;
import com.neu.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired(required = false)
	private OrderMapper orderMapper;

	@Autowired(required = false)
	private OrderItemMapper orderItemMapper;

	@Autowired(required = false)
	private AddressMapper addressMapper;

	@Autowired
	private GoodsService goodsService;

	@Override
	public void insertOrder(Order order) {
		orderMapper.insertSelective(order);
	}

	@Override
	public void deleteById(Integer orderid) {
		orderMapper.deleteByPrimaryKey(orderid);
	}

	@Override
	public List<Order> selectOrderByExample(OrderExample orderExample) {
		return orderMapper.selectByExample(orderExample);
	}

	@Override
	public List<OrderItem> getOrderItemByExample(OrderItemExample orderItemExample) {
		return orderItemMapper.selectByExample(orderItemExample);
	}

	@Override
	public Address getAddressByKey(Integer addressid) {
		return addressMapper.selectByPrimaryKey(addressid);
	}

	@Override
	public void updateOrderByKey(Order order) {
		orderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public Order selectByPrimaryKey(Integer orderid) {
		return orderMapper.selectByPrimaryKey(orderid);
	}

	@Override
	public void insertOrderItem(OrderItem orderItem) {
		orderItemMapper.insertSelective(orderItem);
		Goods goods = goodsService.selectById(orderItem.getGoodsid());
		goods.setVolume(goods.getVolume() + orderItem.getNum());
		goodsService.updateGoodsById(goods);
	}

	@Override
	public List<Order> selectToday() {

		return orderMapper.selectTodayOrders();
	}

	@Override
	public List<Order> selectWeek() {
		return orderMapper.selectWeekOrders();
	}

	@Override
	public List<Order> selectMonth() {
		return orderMapper.selectMonthOrders();
	}

	@Override
	public List<GoodsAndSum> selectGoodsAndSum(List<Integer> orderIds) {
		return orderItemMapper.selectGoodsAndSum(orderIds);
	}

	@Override
	public List<Goods> selectTodayHotGoods() {
		List<Order> orderToday = selectToday();
		if (orderToday.size() != 0) {
			List<Integer> orderIds = new ArrayList();
			for (Order order : orderToday) {
				orderIds.add(order.getOrderid());
			}
			List<GoodsAndSum> hot = selectGoodsAndSum(orderIds);
			List<Goods> gl = new ArrayList();
			for (GoodsAndSum goods : hot) {
				Goods g = goodsService.selectById(goods.getGoodsid());
				g.setVolume(goods.getTotal());
				gl.add(g);
			}
			return gl;
		}
		return null;
	}

	@Override
	public List<Goods> selectWeekHotGoods() {
		List<Order> orderWeek = selectWeek();
		if (orderWeek.size() != 0) {
			List<Integer> orderIds = new ArrayList();
			for (Order order : orderWeek) {
				orderIds.add(order.getOrderid());
			}
			List<GoodsAndSum> hot = selectGoodsAndSum(orderIds);
			List<Goods> gl = new ArrayList();
			for (GoodsAndSum goods : hot) {
				Goods g = goodsService.selectById(goods.getGoodsid());
				g.setVolume(goods.getTotal());
				gl.add(g);
			}
			return gl;
		}
		return null;

	}

	@Override
	public List<Goods> selectMonthHotGoods() {
		List<Order> orderMonth = selectMonth();
		if (orderMonth.size() != 0) {
			List<Integer> orderIds = new ArrayList();
			for (Order order : orderMonth) {
				orderIds.add(order.getOrderid());
			}
			List<GoodsAndSum> hot = selectGoodsAndSum(orderIds);
			List<Goods> gl = new ArrayList();
			for (GoodsAndSum goods : hot) {
				Goods g = goodsService.selectById(goods.getGoodsid());
				g.setVolume(goods.getTotal());
				gl.add(g);
			}
			return gl;
		}
		return null;
	}

	@Override // admin
	public List<DateAndSales> selectSale() {
		List<Order> orderWeek = selectWeek();
		if (orderWeek.size() != 0) {
			List<Integer> orderIds = new ArrayList();
			for (Order order : orderWeek) {
				orderIds.add(order.getOrderid());
			}
			// System.out.println("本周销量统计：" + orderMapper.selectSale(orderIds));
			return orderMapper.selectSale(orderIds);
		}
		return null;

	}

	@Override // admin
	public List<Goods> selectGoodsAndSalesInWeek() {
		List<Order> orderWeek = selectWeek();
		if (orderWeek.size() != 0) {
			List<Integer> orderIds = new ArrayList();
			for (Order order : orderWeek) {
				orderIds.add(order.getOrderid());
			}
			List<GoodsAndSum> gsList = orderItemMapper.selectGoodsAndSum(orderIds);

			List<Goods> gList = new ArrayList();
			for (GoodsAndSum gs : gsList) {
				Goods goods = goodsService.selectById(gs.getGoodsid());
				goods.setNum(gs.getTotal());
				goods.setSales(goods.getNum() * goods.getPrice());
				gList.add(goods);
			}
			// System.out.println("本周商品销量统计：" + gList);
			return gList;
		}
		return null;
	}

}

package com.neu.shop.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.neu.shop.pojo.DateAndSales;
import com.neu.shop.pojo.Goods;
import com.neu.shop.service.OrderService;

@Controller
@RequestMapping("/admin/count")
public class CountController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/show")
	public String show(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		return "adminVolumeCount";
	}

	@RequestMapping("/showGoods")
	public String showGoods(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		return "adminGoodsSales";
	}

	@RequestMapping("/sales")
	public void showSalesMonth(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		List<DateAndSales> list = orderService.selectSale();
		List<String> days = null;
		List<Double> sales = null;
		Map<String, Object> map = new HashMap<>();
		if (list != null) {
			days = new ArrayList();
			sales = new ArrayList();
			for (DateAndSales ds : list) {
				days.add(ds.getDate());
				sales.add(ds.getSales());
			}
		}

		map.put("sales", sales);
		map.put("days", days);

		response.getWriter().println(JSON.toJSONString(map));
	}

	@RequestMapping(value = "/goods", produces = { "application/json;charset=UTF-8" })
	public void showGoodsMonth(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		List<Goods> list = orderService.selectGoodsAndSalesInWeek();
		Map<String, Object> map = new HashMap<>();
		List<String> name = null;
		List<Integer> sales = null;
		List<Integer> volume = null;
		if(list != null) {
			name = new ArrayList();
			sales = new ArrayList();
			volume = new ArrayList();
			
			for (Goods goods : list) {
				name.add(goods.getGoodsname());
				sales.add((int) goods.getSales());
				volume.add(goods.getNum());
			}
		}
		
		map.put("volume", volume);
		map.put("sale", sales);
		map.put("names", name);

		System.out.println(name);

		response.getWriter().println(JSON.toJSONString(map));
	}

}

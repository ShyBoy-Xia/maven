package com.neu.shop.controller.front;

import com.neu.shop.pojo.*;
import com.neu.shop.service.GoodsService;
import com.neu.shop.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CartController {

	@Autowired
	private ShopcartService shopCartService;

	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/addCart")
	public String addCart(Shopcart shopcart, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// 判断是否已经加入购物车
		Shopcart shopCart1 = shopCartService.selectCartByKey(new ShopcartKey(user.getUserid(), shopcart.getGoodsid()));
		if (shopCart1 != null) {
			return "redirect:/showcart";
		}

		// 用户
		shopcart.setUserid(user.getUserid());

		// 加入时间
		shopcart.setCatedate(new Date());

		shopCartService.addShopCart(shopcart);

		// 返回到购物车页面
		return "redirect:/showcart";
	}

	@RequestMapping("/showcart")
	public String showCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		// 获取当前用户的购物车信息
		List<Shopcart> shopCart = shopCartService.selectCartByUser(user.getUserid());

		// 获取购物车中的商品信息
		List<Goods> goodsAndImage = new ArrayList<>();
		for (Shopcart cart : shopCart) {
			Goods goods = goodsService.selectById(cart.getGoodsid());
			List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
			goods.setImagePaths(imagePathList);
			goods.setNum(cart.getGoodsnum());
			goodsAndImage.add(goods);
		}
		//System.out.println(goodsAndImage);
		model.addAttribute("shopcart", goodsAndImage);

		List<Goods> recomList = null;
		if (shopCart != null) {
			recomList = new ArrayList();
			for (Shopcart cart : shopCart) {
				Goods goods = goodsService.selectById(cart.getGoodsid());
				List<Goods> goodsRec = goodsService.selectByRecommend(goods.getCategory2());
				System.out.println("goodsRec" + goodsRec);
				int cnt = 0;
				for(Goods good:goodsRec) {
					if(good.getGoodsid() != cart.getGoodsid()) {
						cnt++;
						List<ImagePath> imagePathList = goodsService.findImagePath(good.getGoodsid());
						good.setImagePaths(imagePathList);
						recomList.add(good);
						if(cnt == 2)
							break;
					}
						
				}		
			}
		}
		System.out.println(recomList);
		model.addAttribute("recomList", recomList);

		return "mycart";
	}

	@RequestMapping(value = "/deleteCart/{goodsid}", method = RequestMethod.GET)
	public String deleteCart(@PathVariable("goodsid") Integer goodsid, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		shopCartService.deleteByKey(new ShopcartKey(user.getUserid(), goodsid));
		return "redirect:/showcart";
	}

	@RequestMapping(value = "/deduceOneCart/{goodsid}", method = RequestMethod.GET)
	public String deduceOneCart(@PathVariable("goodsid") Integer goodsid, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		shopCartService.deduceOne(new ShopcartKey(user.getUserid(), goodsid));
		return "redirect:/showcart";
	}

	@RequestMapping(value = "/addOneCart/{goodsid}", method = RequestMethod.GET)
	public String addOneCart(@PathVariable("goodsid") Integer goodsid, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		shopCartService.addOne(new ShopcartKey(user.getUserid(), goodsid));
		return "redirect:/showcart";
	}

}

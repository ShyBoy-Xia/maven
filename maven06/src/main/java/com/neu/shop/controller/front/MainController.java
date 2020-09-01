package com.neu.shop.controller.front;

import com.neu.shop.pojo.*;
import com.neu.shop.service.CateService;
import com.neu.shop.service.GoodsService;
import com.neu.shop.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private CateService cateService;

    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private OrderService orderService;

    @RequestMapping("/main")
    public String showAllGoods(Model model, HttpSession session) {

        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getUserid();
        }
		
        List<Category> categoryList = cateService.selectAll();
        model.addAttribute("category", categoryList);
        
        Map<Integer,List<CategorySec>> categorySecMap = new HashMap<Integer, List<CategorySec>>();
        for(Category cate: categoryList) {
        	categorySecMap.put(cate.getCateid(),cateService.selectAllSecCategoryByFirst(cate.getCateid()));
        }
        model.addAttribute("catesec", categorySecMap);
        
        Map<Integer,List<Goods>> goodsMap = new HashMap<Integer, List<Goods>>();
        for(Category cate: categoryList) {
        	goodsMap.put(cate.getCateid(),getCateGoods(1,cate.getCateid(),userid));
        }
        model.addAttribute("goodsmap", goodsMap);
        return "main";
    }
    
   
    public List<Goods> getCateGoods(int cate, int cateid, Integer userid) {
        //查询分类
    	List<Goods> goodsList = null;
    	if(cate == 1) {
    		goodsList = goodsService.selectByCategory(cateid, 1);
    	}else {
    		goodsList = goodsService.selectByCategory(cateid, 2);
    	}

        //查询属于刚查到的分类的商品
        List<Goods> goodsAndImage = new ArrayList<>();
        //获取每个商品的图片
        for (Goods goods:goodsList) {
            //判断是否为登录状态
            if (userid == null) {
                goods.setFav(false);
            } else {
                Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(userid, goods.getGoodsid()));
                if (favorite == null) {
                    goods.setFav(false);
                } else {
                    goods.setFav(true);
                }
            }

            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
            goods.setImagePaths(imagePathList);
           // System.out.println(imagePathList.get(0).getPath());
            goodsAndImage.add(goods);
        }
        return goodsAndImage;
    }
    
    @RequestMapping("/ranking")
    public String showGoods(Model model, HttpSession session) {
    	List<Goods> goodsList = orderService.selectTodayHotGoods();
    	List<Goods> goodsAndImage = null;
    	if(goodsList != null) {
    		goodsAndImage = setGoodsImage(goodsList);
    	}	
        model.addAttribute("goodsAndImageDay", goodsAndImage);
        
    	goodsList = orderService.selectWeekHotGoods();
    	System.out.println(goodsList);
    	goodsAndImage = null;
    	if(goodsList != null) {
    		goodsAndImage = setGoodsImage(goodsList);
    	}	
        model.addAttribute("goodsAndImageWeek", goodsAndImage);
        
        goodsList = orderService.selectMonthHotGoods();
    	goodsAndImage = null;
    	if(goodsList != null) {
    		goodsAndImage = setGoodsImage(goodsList);
    	}	
        model.addAttribute("goodsAndImageMonth", goodsAndImage);
        return "ranking";
    }
    
    public List<Goods> setGoodsImage(List<Goods> goodsList){
    	List<Goods> goodsAndImage = new ArrayList<>();
        //获取每个商品的图片
    	int cnt = 0;
        for (Goods goods:goodsList) {
        	cnt++;
            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
            goods.setNum(cnt);
            goods.setImagePaths(imagePathList);
            goodsAndImage.add(goods);
            if(cnt == 10)
            	break;   
        }
        return goodsAndImage;
    }
}

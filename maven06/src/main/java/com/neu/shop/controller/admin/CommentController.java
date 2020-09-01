package com.neu.shop.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.shop.pojo.Comment;
import com.neu.shop.pojo.Goods;
import com.neu.shop.pojo.User;
import com.neu.shop.service.CommentService;
import com.neu.shop.service.GoodsService;
import com.neu.shop.service.UserService;


@Controller
@RequestMapping("/admin/comment")
public class CommentController {
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private GoodsService goodsService;
	
	@RequestMapping("/show")
    public String showActivity(Model model, HttpSession session) {
		List<Comment> cl =  commentService.selectAll();
		for(Comment c:cl) {
			Goods goods = goodsService.selectById(c.getGoodsid());
			c.setGoodsName(goods.getGoodsname());
			
			User user = userService.selectByPrimaryKey(c.getUserid());
			c.setUsername(user.getUsername());
		}
        model.addAttribute("comment",cl);
        
        return "adminComment";
    }
	
	@RequestMapping("/delete")
    public String deleteComment(Integer commentid,Model model, HttpSession session) {
		commentService.deleteById(commentid);
		List<Comment> cl =  commentService.selectAll();
		for(Comment c:cl) {
			Goods goods = goodsService.selectById(c.getGoodsid());
			c.setGoodsName(goods.getGoodsname());
			
			User user = userService.selectByPrimaryKey(c.getUserid());
			c.setUsername(user.getUsername());
		}
        model.addAttribute("comment",cl);
        
        return "adminComment";
    }

}

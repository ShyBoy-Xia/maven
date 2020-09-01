package com.neu.shop.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.shop.pojo.*;
import com.neu.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FrontGoodsController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private CateService cateService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detailGoods(Integer goodsid, Model model, HttpSession session) {

		if (goodsid == null) {
			return "redirect:/main";
		}

		User user = (User) session.getAttribute("user");

		// 要传回的数据存在HashMap中
		Map<String, Object> goodsInfo = new HashMap<String, Object>();

		// 查询商品的基本信息
		Goods goods = goodsService.selectById(goodsid);

		

		if (user == null) {
			goods.setFav(false);
		} else {
			Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(user.getUserid(), goodsid));
			if (favorite == null) {
				goods.setFav(false);
			} else {
				goods.setFav(true);
			}
		}
		//更新评分
		goods.setScore((double) Math.round(commentService.selectScoreAvg(goods.getGoodsid()) * 100) / 100);
		goodsService.updateGoodsById(goods);
		
		// 查询商品类别
		Category category = cateService.selectById(goods.getCategory());

		// 商品图片
		List<ImagePath> imagePath = goodsService.findImagePath(goodsid);

		// 商品评论

		// 商品折扣信息
		Activity activity = activityService.selectByKey(goods.getActivityid());
		goods.setActivity(activity);

		// 返回数据

		goodsInfo.put("cate", category);
		goodsInfo.put("image", imagePath);
		System.out.println(goods);
		goodsInfo.put("goods", goods);
		model.addAttribute("goodsInfo", goodsInfo);
//        model.addAllAttributes(goodsInfo);

		// 评论信息
		CommentExample commentExample = new CommentExample();
		commentExample.or().andGoodsidEqualTo(goods.getGoodsid());
		List<Comment> commentList = commentService.selectByExample(commentExample);
		for (Integer i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			User commentUser = userService.selectByPrimaryKey(comment.getUserid());
			comment.setUserName(commentUser.getUsername());
			comment.setUserImage(commentUser.getImage());
			commentList.set(i, comment);
		}
		model.addAttribute("commentList", commentList);

		Map<String, Integer> scoreMap = new HashMap();
		for (int i = 1; i <= 5; i++) {
			List<Comment> scoreList = getScore(i, goods.getGoodsid());
			if (scoreList.size() != 0) {
				double temp = scoreList.size() * 1.0 / commentList.size();
				System.out.println(temp);
				scoreMap.put(i + "", (int) (temp * 100));
				
			} else {
				scoreMap.put(i + "", 0);
			}System.out.println("map" + " " + i + " " + scoreMap.get(i + ""));
		}

		model.addAttribute("scoreMap", scoreMap);

		return "detail";
	}

	public List<Comment> getScore(int score, int id) {
		CommentExample commentExample = new CommentExample();
		commentExample.or().andGoodsidEqualTo(id).andPointEqualTo(score);
		return commentService.selectByExample(commentExample);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchGoods(@RequestParam(value = "page", defaultValue = "1") Integer pn, String keyword, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("user");

		// 一页显示几个数据
		PageHelper.startPage(pn, 16);

		// 查询数据
		List<Goods> goodsList = goodsService.selectByKeyword(keyword);

		// 获取图片地址
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);

			List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());

			goods.setImagePaths(imagePathList);

			// 判断是否收藏
			if (user == null) {
				goods.setFav(false);
			} else {
				Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(user.getUserid(), goods.getGoodsid()));
				if (favorite == null) {
					goods.setFav(false);
				} else {
					goods.setFav(true);
				}
			}

			goodsList.set(i, goods);
		}

		// 显示几个页号
		PageInfo page = new PageInfo(goodsList, 5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("keyword", keyword);

		return "search";
	}

	@RequestMapping(value = "/sortProduct", method = RequestMethod.GET)
	public String sortGoods(@RequestParam(value = "page", defaultValue = "1") Integer pn, Integer sort, String keyword,
			Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		// 一页显示几个数据
		PageHelper.startPage(pn, 16);

		// 查询数据
		List<Goods> goodsList = goodsService.selectBySort(keyword, sort);

		// 获取图片地址
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);

			List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());

			goods.setImagePaths(imagePathList);

			// 判断是否收藏
			if (user == null) {
				goods.setFav(false);
			} else {
				Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(user.getUserid(), goods.getGoodsid()));
				if (favorite == null) {
					goods.setFav(false);
				} else {
					goods.setFav(true);
				}
			}

			goodsList.set(i, goods);
		}

		// 显示几个页号
		PageInfo page = new PageInfo(goodsList, 5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("keyword", keyword);

		return "search";
	}

	@RequestMapping(value = "/sortByCate", method = RequestMethod.GET)
	public String sortGoodsByCategory(@RequestParam(value = "page", defaultValue = "1") Integer pn, Integer sort,
			int cate, int cateid, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		// 一页显示几个数据
		PageHelper.startPage(pn, 16);

		// 查询数据
		List<Goods> goodsList = goodsService.selectByCategory(cateid, cate);

		// 获取图片地址
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);

			List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());

			goods.setImagePaths(imagePathList);

			// 判断是否收藏
			if (user == null) {
				goods.setFav(false);
			} else {
				Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(user.getUserid(), goods.getGoodsid()));
				if (favorite == null) {
					goods.setFav(false);
				} else {
					goods.setFav(true);
				}
			}

			goodsList.set(i, goods);
		}

		// 显示几个页号
		PageInfo page = new PageInfo(goodsList, 5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("cateid", cateid);
		model.addAttribute("cateInt", cate);
		return "category";
	}

	@RequestMapping("/collect")
	@ResponseBody
	public Msg collectGoods(Integer goodsid, HttpSession session) {
		// 取登录用户信息,未登录重定向至登录页面
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return Msg.fail("收藏失败");
		}

		// 添加收藏
		Favorite favorite = new Favorite();
		favorite.setCollecttime(new Date());
		favorite.setGoodsid(goodsid);
		favorite.setUserid(user.getUserid());

		goodsService.addFavorite(favorite);

		return Msg.success("收藏成功");
	}

	@RequestMapping("/deleteCollect")
	@ResponseBody
	public Msg deleteFavGoods(Integer goodsid, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return Msg.fail("取消收藏失败");
		}

		// 删除收藏
		goodsService.deleteFavByKey(new FavoriteKey(user.getUserid(), goodsid));

		return Msg.success("取消收藏成功");
	}

	@RequestMapping("/category")
	public String getCateGoods(Integer cate, Integer cateid, String catename,
			@RequestParam(value = "page", defaultValue = "1") Integer pn, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		// 一页显示几个数据
		PageHelper.startPage(pn, 16);

		// 查询数据
		List<Goods> goodsList = goodsService.selectByCategory(cateid, cate);

		// 获取图片地址
		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = goodsList.get(i);

			List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());

			goods.setImagePaths(imagePathList);

			// 判断是否收藏
			if (user == null) {
				goods.setFav(false);
			} else {
				Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(user.getUserid(), goods.getGoodsid()));
				if (favorite == null) {
					goods.setFav(false);
				} else {
					goods.setFav(true);
				}
			}

			goodsList.set(i, goods);
		}

		// 显示几个页号
		PageInfo page = new PageInfo(goodsList, 5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("cate", catename);
		model.addAttribute("cateid", cateid);
		model.addAttribute("cateInt", cate);
		return "category";
	}

	@RequestMapping("/comment")
	@ResponseBody
	public Msg comment(Comment comment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return Msg.fail("评论失败");
		}
		comment.setUserid(user.getUserid());
		Date date = new Date();
		comment.setCommenttime(date);
		commentService.insertSelective(comment);
		
		Goods goods = goodsService.selectById(comment.getGoodsid());
		goods.setScore((double)Math.round(commentService.selectScoreAvg(comment.getGoodsid())*100)/100);
		goodsService.updateGoodsById(goods);
		
		return Msg.success("评论成功");
	}
}

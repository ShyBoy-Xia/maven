package com.neu.shop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.shop.pojo.*;
import com.neu.shop.service.CateService;
import com.neu.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private CateService cateService;
    

    @RequestMapping("/show")
    public String goodsManage( HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        List<Goods> goodsList = goodsService.selectAll();
        System.out.println("商品列表：" + goodsList);
        for(Goods goods:goodsList) {
        	goods.setCate1(cateService.selectById(goods.getCategory()).getCatename());
        	goods.setCate2(cateService.selectSecById(goods.getCategory2()).getCatesecname());
        }
        List<Category> categoryList = cateService.selectAll();
        model.addAttribute("categoryList",categoryList);
        
        List<CategorySec> categorySecList = cateService.selectAllCate2();
        model.addAttribute("categorySecList",categorySecList);

        model.addAttribute("pageInfo", goodsList);

        return "adminAllGoods";
    }

    @RequestMapping("/add")
    public String showAdd(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        if(!msg.equals("")) {
            model.addAttribute("msg", msg);
        }

        List<Category> categoryList = cateService.selectAll();
        model.addAttribute("categoryList",categoryList);
        
        List<CategorySec> categorySecList = cateService.selectAllCate2();
        model.addAttribute("categorySecList",categorySecList);

        //还需要查询分类传给addGoods页面
        return "addGoods";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateGoods(Goods goods, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
       /* goods.setGoodsid(goodsid);*/
        goodsService.updateGoodsById(goods);
        return Msg.success("更新成功!");
    }

    @RequestMapping(value = "/delete/{goodsid}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteGoods(@PathVariable("goodsid")Integer goodsid) {
        goodsService.deleteGoodsById(goodsid);
        return Msg.success("删除成功!");
    }

    @RequestMapping("/addGoodsSuccess")
    public String addGoods(Goods goods,
                           @RequestParam MultipartFile[] fileToUpload,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           RedirectAttributes redirectAttributes) throws IOException {

    	goods.setVolume(0);
    	goods.setScore(0.0);
        goods.setUptime(new Date());
        goods.setActivityid(1);
        int id = goodsService.addGoods(goods);
        goods = goodsService.selectById(id);

        for(MultipartFile multipartFile:fileToUpload){
            if (multipartFile != null){

                String realPath = request.getSession().getServletContext().getRealPath("/");
                //图片路径=项目在本地磁盘的路径\shop\target\shop\shopimage
                String imageName = UUID.randomUUID().toString().replace("-", "") + multipartFile.getOriginalFilename();
                String imagePath = "image" + File.separatorChar + "goods" + File.separatorChar + imageName;
               
                System.out.println(goods.getGoodsid() + imagePath);
                //把图片路径存入数据库中
                goodsService.addImagePath(new ImagePath(null, goods.getGoodsid(),imagePath));
                //存图片
                multipartFile.transferTo(new File(realPath + imagePath));
            }
        }

        redirectAttributes.addFlashAttribute("succeseMsg","商品添加成功!");

        return "redirect:/admin/goods/add";
    }

    @RequestMapping("/addCategory")
    public String addCategory(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        List<Category> categoryList;
        categoryList = cateService.selectAll();
        model.addAttribute("categoryList", categoryList);
        
        List<CategorySec> categorySecList = cateService.selectAllCate2();
        model.addAttribute("categorySecList", categorySecList);
        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }
        return "addCategory";
    }

    

    @RequestMapping("/addCategoryResult")
    public String addCategoryResult(Category category,Model addCategoryResult,RedirectAttributes redirectAttributes){
        List<Category> categoryList = cateService.selectRepeat(category.getCatename());
        if (!categoryList.isEmpty())
        {
            redirectAttributes.addAttribute("succeseMsg","分类已存在");
            return "redirect:/admin/goods/addCategory";
        }
        else {
            cateService.insertSelective(category);
            redirectAttributes.addFlashAttribute("succeseMsg","分类添加成功!");
            return "redirect:/admin/goods/addCategory";
        }
    }

    @RequestMapping("/saveCate")
    @ResponseBody
    public Msg saveCate(Category category){
    	List<Category> categoryList = cateService.selectRepeat(category.getCatename());
        if (categoryList.isEmpty())
        {
            cateService.updateByPrimaryKeySelective(category);
            return Msg.success("更新成功");
        }
        else return Msg.success("名字已经存在");
    }

    @RequestMapping("/deleteCate")
    @ResponseBody
    public Msg deleteCate(Category category){
        cateService.deleteByPrimaryKey(category.getCateid());
        return Msg.success("删除成功");
    }
    
    //修改二级分类
    
    @RequestMapping("/addCategory2")
    public String addCategory2(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        List<Category> categoryList;
        categoryList = cateService.selectAll();
        model.addAttribute("categoryList", categoryList);
        
        Map<Integer,List<CategorySec>> cateMap = new HashMap();
        for(Category category:categoryList) {
        	cateMap.put(category.getCateid(), cateService.selectAllSecCategoryByFirst(category.getCateid()));
        }
        model.addAttribute("cateMap",cateMap);
        
        List<CategorySec> categorySecList = cateService.selectAllCate2();
        model.addAttribute("categorySecList", categorySecList);
        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }
        return "addCategory2";
    }

    

    @RequestMapping("/addCategory2Result")
    public String addCategory2Result(CategorySec categorySec,Model addCategoryResult,RedirectAttributes redirectAttributes){
        List<Category> categoryList = cateService.selectRepeat(categorySec.getCatesecname());
        if (!categoryList.isEmpty())
        {
            redirectAttributes.addAttribute("succeseMsg","分类已存在");
            return "redirect:/admin/goods/addCategory";
        }
        else {
            cateService.insertSelective(categorySec);
            redirectAttributes.addFlashAttribute("succeseMsg","分类添加成功!");
            return "redirect:/admin/goods/addCategory2";
        }
    }

    @RequestMapping("/saveCate2")
    @ResponseBody
    public Msg saveCate2(CategorySec categorySec){
    	List<CategorySec> categorySecList = cateService.selectRepeatSec(categorySec.getCatesecname());
        if (categorySecList.isEmpty())
        {
        	System.out.println(categorySec);
            cateService.updateByPrimaryKeySelective(categorySec);
            return Msg.success("更新成功");
        }
        else return Msg.success("名字已经存在");
    }

    @RequestMapping("/deleteCate2")
    @ResponseBody
    public Msg deleteCate2(CategorySec categorySec){
        cateService.deleteCateSecByPrimaryKey(categorySec.getCatesecid());
        return Msg.success("删除成功");
    }
}

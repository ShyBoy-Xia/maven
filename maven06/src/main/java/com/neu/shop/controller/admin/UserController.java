package com.neu.shop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.shop.pojo.*;
import com.neu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/show")
    public String userManage(HttpServletResponse response, Model model) {
    	List<User> userList = userService.selectByExample(new UserExample());
    	model.addAttribute("userList", userList);
        return "userManage";
    }

    @RequestMapping("/delete/{userid}")
    public String deleteUser(@PathVariable("userid")Integer userid) {
        userService.deleteUserById(userid);
        return "redirect:/admin/user/show";
    }
}

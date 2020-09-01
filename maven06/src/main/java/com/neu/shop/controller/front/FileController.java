package com.neu.shop.controller.front;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neu.shop.pojo.User;
import com.neu.shop.service.UserService;

@Controller
public class FileController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/fileUpload")
	public String handleFormUpload(HttpServletRequest request,@RequestParam("filename") List<MultipartFile> file) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user;
		user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		if (!file.isEmpty()) {
			for(MultipartFile f:file) {
				String realPath = request.getSession().getServletContext().getRealPath("/");
                String imageName = UUID.randomUUID().toString().replace("-", "") + f.getOriginalFilename();
                String imagePath = "image" + File.separatorChar + "user" + File.separatorChar + imageName;
          
				f.transferTo(new File(realPath + imagePath));
				user.setImage(imagePath);
				userService.updateByPrimaryKeySelective(user);
				System.out.println(user);
			}
		}
		return "information";
	}
 

}

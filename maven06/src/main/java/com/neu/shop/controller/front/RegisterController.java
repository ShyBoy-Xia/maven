package com.neu.shop.controller.front;

import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.shop.pojo.User;
import com.neu.shop.pojo.UserExample;
import com.neu.shop.service.UserService;
import com.neu.shop.util.HtmlText;
import com.neu.shop.util.JavaMailUtil;
import com.neu.shop.util.RandomUtil;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String registerView() {
		return "newRegister";
	}
	
	@RequestMapping("/login")
	public String loginView() {
		return "login";
	}
	
	@RequestMapping("/RegisterResult")
	public String registerResult(User user, Model registerResult,HttpSession session) {
		String sessionCode = (String) session.getAttribute("code");
		System.out.println("code:" + sessionCode + "user:" + user.getCode());
		List<User> userList = new ArrayList<>();
		UserExample userExample = new UserExample();
		userExample.or().andUsernameLike(user.getUsername());
		userList = userService.selectByExample(userExample);
		
		userExample = new UserExample();
		userExample.or().andEmailEqualTo(user.getEmail());
		List<User> userEmailList = userService.selectByExample(userExample);
		if(user.getCode().equals(sessionCode)) {
			if (!userList.isEmpty()) {
				registerResult.addAttribute("errorMsg", "用户名被占用");
				session.removeAttribute("code");
				return "newRegister";
			} else if(!userEmailList.isEmpty()) {
				registerResult.addAttribute("errorMsg", "邮箱已被注册");
				session.removeAttribute("code");
				return "newRegister";
			}
			else {
				Date RegTime = new Date();
				user.setRegtime(RegTime);
				userService.insertSelective(user);
				session.removeAttribute("code");
				return "redirect:/main";
			}
		}else {
			registerResult.addAttribute("errorMsg", "验证码错误");
			session.removeAttribute("code");
			return "newRegister";
		}
		
	}
	
	@RequestMapping("/registerMailQuest")
	public void registerMailCode(HttpServletRequest req, HttpServletResponse resp,HttpSession session) {
		System.out.println("邮箱发送功能");
		try {
			String email = req.getParameter("email");
			JavaMailUtil.receiveMailAccount = email; // 给用户输入的邮箱发送邮件
			
			final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", JavaMailUtil.emailSMTPHost);
            props.put("mail.smtp.port", "465");
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.ssl.enable", "true");
            //props.put("mail.smtp.host", "smtp.office365.com");
            // 发件人的账号
            props.put("mail.user", JavaMailUtil.emailAccount);
            //发件人的密码
            props.put("mail.password", JavaMailUtil.emailPassword);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);

			// 设置debug，可以查看详细的发送log
            mailSession.setDebug(true);
			// 3、创建一封邮件
			String code = RandomUtil.getRandom();
			System.out.println("邮箱验证码：" + code);
			session.setAttribute("mailCode", code);
			
			String html = HtmlText.html(code);
			MimeMessage message = JavaMailUtil.creatMimeMessage(mailSession, JavaMailUtil.emailAccount,
					JavaMailUtil.receiveMailAccount, html);
 
			// 4、根据session获取邮件传输对象
			Transport transport = mailSession.getTransport();
			// 5、使用邮箱账号和密码连接邮箱服务器emailAccount必须与message中的发件人邮箱一致，否则报错
			transport.connect(JavaMailUtil.emailAccount, JavaMailUtil.emailPassword);
			// 6、发送邮件,发送所有收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			// 7、关闭连接
			transport.close();
			//  写入session
			req.getSession().setAttribute("code", code);
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("error", "邮件发送失败");
		}
	}

}

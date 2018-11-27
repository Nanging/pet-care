package com.stu.petc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.stu.petc.beans.User;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.util.CookieUtil;
import com.stu.petc.util.SpringUtil;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqUser;
import com.stu.petc.web.UserSession;

import io.micrometer.core.instrument.util.JsonUtils;

@Controller
public class UserController {
	@Autowired
	private UserMapper mapper;
	
	
	@GetMapping("/login")
	public String login() {
		System.out.println("-----------------456----------------");
		return "login";
	}
	
	@ResponseBody
	@PostMapping("/login")
	public LoginResponse signIn(@RequestBody ReqUser reqUser) {
		System.out.println("-----------------"+reqUser+"----------------");
//		for (Object string : objects) {
//			System.out.println(string);
//		}
		
//		reqUser = new ReqUser();
		User user = new User();
		System.out.println("-----------------0*----["+reqUser+"]------------");
		if (reqUser!=null) {
			user = mapper.getUserByName(reqUser.getUsername());
			
			if (user == null) {
				System.out.println("----------------1*-----["+reqUser.getUsername()+"]------------");
				return new LoginResponse(-1, "NOT EXISTS", new User());
			}else {
				System.out.println("--------------2*-------------------");
				return new LoginResponse(0, "SUCCESS", user);
			}
		}
		System.out.println("-----------------3*----------------");
		return new LoginResponse(-2, "FAIL", user);
	}
	@ResponseBody
	@PostMapping("/signup")
	public LoginResponse signUp(@RequestBody ReqUser reqUser) {
		User user = new User();
		System.out.println("-----------------0+-"+reqUser+"---------------");
		if (reqUser!=null) {
			
			if (null==mapper.getUserByName(reqUser.getUsername())) {
				System.out.println("-----------------1+----------------");
				return new LoginResponse(0, "SUCCESS", user);
			}else {
				System.out.println("-----------------2+----------------");
				return new LoginResponse(-1, "EXISTS", new User());
			}
		}
		System.out.println("-----------------3+----------------");
		return new LoginResponse(-2, "FAIL", user);
	}
	
}

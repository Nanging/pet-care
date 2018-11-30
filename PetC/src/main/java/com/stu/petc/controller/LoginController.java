package com.stu.petc.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.spec.DESKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.stu.petc.service.UserRedisService;
import com.stu.petc.util.CookieUtil;
import com.stu.petc.util.SpringUtil;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqUser;
import com.stu.petc.web.UserSession;

import io.micrometer.core.instrument.util.JsonUtils;

@Controller
public class LoginController {
	@Autowired
	private UserMapper mapper;
	@Autowired
	private UserRedisService service;

	@GetMapping("/login")
	public String login(Map<String, Object> map,HttpServletRequest request,
			HttpServletResponse response) {
		String username="";
		String password="";
		System.out.println("["+request.getSession().getAttribute("userStatus")+"]");
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						/**
						 * check user
						 */
						username = cookie.getValue();
						password = "123";
					}
				}
			}
		}
		String userStatus = request.getSession().getAttribute("userStatus").toString();
		if (null==userStatus) {
			map.put("status", "ERROR");
			map.put("msg", "Unexpected Error");
		}else if ("SAVED".equals(userStatus)) {
			map.put("status", "SAVED");
			map.put("loginName", username);
			map.put("loginPassword", password);
		}else if ("DEFAULT".equals(userStatus)) {
			map.put("status", "DEFAULT");
		}else if ("FAILED".equals(userStatus)) {
			map.put("loginName", username);
			map.put("loginPassword", password);
			map.put("status", "FAILED");
			map.put("msg", "Sign In First!");
		}else {
			map.put("status", "ACCESS DENIED");
			map.put("msg", "Sign In First!");
		}
		request.getSession().removeAttribute("userStatus");
		return "login";
	}

	@GetMapping("/main")
	public String index() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		return "main";
	}

	@GetMapping("/autoSignIn")
	public String autoSignIn() {
		
		return "main";
	}
	
	@ResponseBody
	@PostMapping("/login")
	public LoginResponse signIn(@RequestBody ReqUser reqUser, HttpServletRequest request,HttpServletResponse response) {
		User user = new User();
		if (reqUser != null && null != reqUser.getUsername() && null != reqUser.getPassword()) {
			/**
			 * check
			 */
			
			if (reqUser.getUsername().length() > 16 || reqUser.getUsername().isEmpty()
					|| reqUser.getPassword().length() > 16 || reqUser.getPassword().isEmpty()) {
				return new LoginResponse(-3, "WRONG ACCESS", null);
			}
			user = mapper.getUserByName(reqUser.getUsername());
			if (user != null && reqUser.getPassword().equals(user.getPassword())) {

				HttpSession session = request.getSession();
				String sessionId = session.getId();
				service.addUserSession(user.getUsername(), sessionId);
				
				Cookie singleCookie = new Cookie("userStatus", "SAVED");
				singleCookie.setPath("/");
				singleCookie.setMaxAge(-1);
				response.addCookie(singleCookie);
				
				Cookie cookie = new Cookie("loginStatus", user.getUsername());
				cookie.setPath("/");
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				return new LoginResponse(0, "SUCCESS", null);
			}
			return new LoginResponse(-1, "Invalid username or password", null);
		}
		return new LoginResponse(-2, "UNEXPECTED ERROR", null);
	}

	@ResponseBody
	@PostMapping("/signup")
	public LoginResponse signUp(@RequestBody ReqUser reqUser) {
		User user = new User();

		if (reqUser != null && null != reqUser.getUsername() && null != reqUser.getPassword()
				&& null != reqUser.getPhone()) {
			if (null == mapper.getUserByName(reqUser.getUsername())) {
				if (reqUser.getUsername().length() > 16 || reqUser.getUsername().isEmpty()
						|| reqUser.getPassword().length() > 16 || reqUser.getPassword().isEmpty()
						|| reqUser.getPhone().length() > 16 || reqUser.getPhone().isEmpty()) {
					return new LoginResponse(-3, "WRONG ACCESS", null);
				}
				/**
				 * add new user
				 */
//				user.setUsername(reqUser.getUsername());
//				user.setPassword(reqUser.getPassword());
//				user.setUser_tel(reqUser.getPhone());
//				if (1==mapper.add(user)) {
//					return new LoginResponse(0, "SUCCESS", null);
//				}
				if (Pattern.matches("1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}", reqUser.getPhone())) {
					System.out.println("Wrong phone number");
				}
				return new LoginResponse(0, "SUCCESS", null);
			} else {
				return new LoginResponse(-1, "Username exists", null);
			}
		}
		return new LoginResponse(-2, "UNEXPECTED ERROR", null);
	}

}

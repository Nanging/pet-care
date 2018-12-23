package com.stu.petc.controller;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.petc.beans.User;
import com.stu.petc.mapper.NoteMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.CheckUnreadService;
import com.stu.petc.service.UserRedisService;
import com.stu.petc.util.Encoder;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqUser;

@Controller
public class LoginController {
	@Autowired
	private UserMapper mapper;
	@Autowired
	private UserRedisService service;
	@Autowired
	CheckUnreadService unreadService;
	
	@GetMapping("/login")
	public String login(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		String username = "";
		String password = "";
		System.out.println("[" + request.getSession().getAttribute("userStatus") + "]");
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						/**
						 * check user
						 */
						String[] token = cookie.getValue().split("_");
						username = token[0];
						password = Encoder.decryptBasedDes(token[1]);
					}
				}
			}
		}
		String userStatus = request.getSession().getAttribute("userStatus").toString();
		if (null == userStatus) {
			map.put("status", "ERROR");
			map.put("msg", "Unexpected Error");
		} else if ("SAVED".equals(userStatus)) {
			map.put("status", "SAVED");
			map.put("loginName", username);
			map.put("loginPassword", password);
		} else if ("DEFAULT".equals(userStatus)) {
			map.put("status", "DEFAULT");
		} else if ("FAILED".equals(userStatus)) {
			map.put("loginName", username);
			map.put("loginPassword", password);
			map.put("status", "FAILED");
			map.put("msg", "Sign In First!");
		} else {
			map.put("status", "ACCESS DENIED");
			map.put("msg", "Sign In First!");
		}
		request.getSession().removeAttribute("userStatus");
		return "login";
	}

	@GetMapping("/main")
	public String index(HttpServletRequest request, Model map) {
		String username = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						/**
						 * check user
						 */
						String[] token = cookie.getValue().split("_");
						username = token[0];
						HttpSession session = request.getSession();
						String sessionId = session.getId();
						String currentSessionID = service.getUserSession(cookie.getValue());
						System.out.println("[currentSessionID:" + currentSessionID + "]");
						if (sessionId.equals(currentSessionID)) {
							map.addAttribute("username", username);
							int unread = unreadService.checkUnread(mapper.getUserByName(username).getUser_id());
							if (unread>0) {
								map.addAttribute("unread", unread);
							}
						}
					}
				}
			}
		}
		
		return "main";
	}

	@GetMapping("/pleaseSignIn")
	public String autoSignIn() {

		return "redirectLogin";
	}

	@ResponseBody
	@PostMapping("/login")
	public LoginResponse signIn(@RequestBody ReqUser reqUser, HttpServletRequest request,
			HttpServletResponse response) {
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
			String password = Encoder.encryptBasedDes(reqUser.getPassword());

			if (user != null && user.getPassword().equals(password)) {

				HttpSession session = request.getSession();
				String sessionId = session.getId();
				service.addUserSession(user.getUsername() + "_" + password, sessionId);

				Cookie singleCookie = new Cookie("userStatus", "SAVED");
				singleCookie.setPath("/");
				singleCookie.setMaxAge(-1);
				response.addCookie(singleCookie);

				Cookie cookie = new Cookie("loginStatus", user.getUsername() + "_" + password);
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
				if (!Pattern.matches(
						"^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$",
						reqUser.getPhone())) {
					System.out.println("Wrong phone number");
					System.out.println(reqUser.getPhone());
					return new LoginResponse(-4, "WRONG PHONE PATTERN", null);
				}
				/**
				 * add new user
				 */
				user.setUsername(reqUser.getUsername());
				user.setPassword(Encoder.encryptBasedDes(reqUser.getPassword()));
				user.setUser_tel(reqUser.getPhone());
				if (1 == mapper.add(user)) {
					return new LoginResponse(0, "SUCCESS", null);
				}
				/**
				 * re phone number
				 */

//				return new LoginResponse(0, "SUCCESS", null);
			} else {
				return new LoginResponse(-1, "Username exists", null);
			}
		}
		return new LoginResponse(-2, "UNEXPECTED ERROR", null);
	}

}

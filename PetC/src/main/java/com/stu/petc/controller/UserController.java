package com.stu.petc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.petc.beans.User;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqUser;

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
		System.out.println("-----------------" + reqUser + "----------------");
//		for (Object string : objects) {
//			System.out.println(string);
//		}

//		reqUser = new ReqUser();
		User user = new User();
		System.out.println("-----------------0*----[" + reqUser + "]------------");
		if (reqUser != null) {
			user = mapper.getUserByName(reqUser.getUsername());

			if (user == null) {
				System.out.println("----------------1*-----[" + reqUser.getUsername() + "]------------");
				return new LoginResponse(-1, "NOT EXISTS", new User());
			} else {
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
		System.out.println("-----------------0+-" + reqUser + "---------------");
		if (reqUser != null) {

			if (null == mapper.getUserByName(reqUser.getUsername())) {
				System.out.println("-----------------1+----------------");
				return new LoginResponse(0, "SUCCESS", user);
			} else {
				System.out.println("-----------------2+----------------");
				return new LoginResponse(-1, "EXISTS", new User());
			}
		}
		System.out.println("-----------------3+----------------");
		return new LoginResponse(-2, "FAIL", user);
	}

}

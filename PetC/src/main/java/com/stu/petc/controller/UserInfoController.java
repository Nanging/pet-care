package com.stu.petc.controller;

import static org.assertj.core.api.Assertions.in;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.mapper.UserInfoMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.UserInfoService;
import com.stu.petc.util.Encoder;

@Controller
public class UserInfoController {

	@Autowired
	UserInfoService service;
	
	@RequestMapping("/user/info")
	public String userInfo(HttpServletRequest request,ModelAndView model) {
		String username = "";
		Cookie[] cookies = request.getCookies();
		if (null!=cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						/**
						 * check user
						 */
						String[] token = cookie.getValue().split("_");
						username = token[0];
					}
				}
			}		
		}
		if (username==null) {
			System.out.println("[NUEXPECT ERROR]");
		}else {
			int id = service.getIDByName(username);
			List<FosterNote> fosterList = service.getAllFosterByUser(id);
			for (FosterNote fosterNote : fosterList) {
				System.out.println(fosterNote);
			}
			List<AdoptionNote> adoptionList = service.getAllAdoptionByUser(id);
			for (AdoptionNote adoptionNote : adoptionList) {
				System.out.println(adoptionNote);
			}
			List<ShareNote> shareList = service.getAllShareByUser(id);
			for (ShareNote shareNote : shareList) {
				System.out.println(shareNote);
			}
			model.addObject("fosterList", fosterList);
			model.addObject("shareList", shareList);
			model.addObject("adoptionList", adoptionList);
			
		}
		return "userInfo";
	}
	
	
	
}

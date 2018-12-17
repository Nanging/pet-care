package com.stu.petc.controller;

import static org.assertj.core.api.Assertions.in;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.beans.AdoptionCandidate;
import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.FosterageCandidate;
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
	public String userInfo(HttpServletRequest request,Model map) {
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
			map.addAttribute("fosterList", fosterList);
			map.addAttribute("shareList", shareList);
			map.addAttribute("adoptionList", adoptionList);
			
		}
		return "userInfo";
	}
	@RequestMapping("/user/adopt/detail/{id}")
	public String getAdoptionDetail(@PathVariable("id") Integer id){
		
		return "userAdoptionDetailPage";
	}
	@RequestMapping("/user/foster/detail/{id}")
	public String getFosterageDetail(@PathVariable("id") Integer id){
		System.out.println("id="+id);
		return "userFosterageDetailPage";
	}
	@RequestMapping("/user/share/detail/{id}")
	public String getShareDetail(@PathVariable("id") Integer id){
		System.out.println("id="+id);
		return "userShareDetailPage";
	}
	
	
	
	
	
	
	@RequestMapping("/user/foster/delete/{id}")
	@ResponseBody
	public String deleteFosterage(@PathVariable("id") Integer id) {
		
		service.deleteFosterageByID(id);
		return "SUCCESS";
	}
	@RequestMapping("/user/adoption/delete/{id}")
	@ResponseBody
	public String deleteAdoption(@PathVariable("id") Integer id) {
		
		service.deleteAdoptionByID(id);
		return "SUCCESS";
	}
	@RequestMapping("/user/share/delete/{id}")
	@ResponseBody
	public String deleteShare(@PathVariable("id") Integer id) {
		
		service.deleteShareByID(id);
		return "SUCCESS";
	}
	
	@RequestMapping("/user/foster/show/{id}")
	public String showFosterageCandidates(@PathVariable("id") Integer id,Model map) {
		List<FosterageCandidate> list = service.getFosterageCandidates(id);
		map.addAttribute("candidate", list);
		map.addAttribute("type", "fosterage");
		return "userInfoDetailPage";
	}
	
	@RequestMapping("/user/adoption/show/{id}")
	public String showAdoptionCandidates(@PathVariable("id") Integer id,Model map) {
		List<AdoptionCandidate> list = service.getAdoptionCandidates(id);
		map.addAttribute("candidate", list);
		map.addAttribute("type", "adoption");
		return "userInfoDetailPage";
	}
	
}

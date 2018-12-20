package com.stu.petc.controller;

import static org.assertj.core.api.Assertions.in;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.beans.AdoptionCandidate;
import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.FosterageCandidate;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.UserInfoMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.UserInfoService;
import com.stu.petc.util.Encoder;
import com.stu.petc.util.Tools;

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
			User user = service.getUserByID(id);
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
//			map.addAllAttributes(attributeValues)
			map.addAttribute("username", username);
			map.addAttribute("tel", user.getUser_tel());
			map.addAttribute("fosterList", fosterList);
			map.addAttribute("shareList", shareList);
			map.addAttribute("adoptionList", adoptionList);
			
		}
		return "userInfo";
	}
	@RequestMapping("/user/adopt/detail/{id}")
	public String getAdoptionDetail(@PathVariable("id") Integer id, Map<String, Object> model) throws FileNotFoundException{
		AdoptionNote adoptionNote = service.getAdoptionByID(id);
		System.out.println(adoptionNote+"-"+id);
		
		String basepath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/adoption/" + String.valueOf(id) + "/";
		System.out.println(basepath);
		
		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if(directory.isDirectory()){
			File []files = directory.listFiles();
			for(File fileIndex:files){
				if(fileIndex.isDirectory()){
				
				}else {
				// if is file
				paths.add("../staticImg/adoption/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}
		
		System.out.println("paths:" + paths);
		
		User user = service.getUserByID(adoptionNote.getEditor());
		service.setAdoptionUnreadZero(id);
		model.put("publisher", user.getUsername());
		model.put("phone", user.getUser_tel());
		model.put("adoption", adoptionNote);
		model.put("paths", paths);
		
		return "userAdoptionDetailPage";
	}
	@RequestMapping("/user/foster/detail/{id}")
	public String getFosterageDetail(@PathVariable("id") Integer id, Map<String, Object> model) throws FileNotFoundException{
		FosterNote fosterNote = service.getFosterByID(id);
		System.out.println(fosterNote+"-"+id);
		
		String basepath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/forsterage/" + String.valueOf(id) + "/";
		System.out.println(basepath);
		
		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if(directory.isDirectory()){
			File []files = directory.listFiles();
			for(File fileIndex:files){
				if(fileIndex.isDirectory()){
				
				}else {
				// if is file
				paths.add("../staticImg/forsterage/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}
		
		System.out.println("paths:" + paths);
		
		User user = service.getUserByID(fosterNote.getEditor());
		service.setFosterageUnreadZero(id);
//		System.out.println(Tools.DateFormat(fosterNote.getPublish_date()));
		model.put("publisher", user.getUsername());
		model.put("foster", fosterNote);
		model.put("paths", paths);
		
		return "userFosterageDetailPage";
	}
	@RequestMapping("/user/share/detail/{id}")
	public String getShareDetail(@PathVariable("id") Integer id, Map<String, Object> model) throws FileNotFoundException{
		ShareNote shareNote = service.getShareByID(id);
		System.out.println(shareNote+"-"+id);
		
		String basepath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/share/" + String.valueOf(id) + "/";
		System.out.println(basepath);
		
		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if(directory.isDirectory()){
			File []files = directory.listFiles();
			for(File fileIndex:files){
				if(fileIndex.isDirectory()){
				
				}else {
				// if is file
				paths.add("../staticImg/share/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}
		
		User user = service.getUserByID(shareNote.getEditor());
		service.setShareUnreadZero(id);
		model.put("publisher", user.getUsername());
		model.put("share", shareNote);
		model.put("paths", paths);
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
		System.out.println("get "+id);
		List<FosterageCandidate> list = service.getFosterageCandidates(id);
		map.addAttribute("candidates", list);
		map.addAttribute("type", "fosterage");
		return "userInfoDetailPage";
	}
	
	@RequestMapping("/user/adoption/show/{id}")
	public String showAdoptionCandidates(@PathVariable("id") Integer id,Model map) {
		List<AdoptionCandidate> list = service.getAdoptionCandidates(id);
		map.addAttribute("id", id);
		map.addAttribute("candidates", list);
		map.addAttribute("type", "adoption");
		return "userInfoDetailPage";
	}
	
	@RequestMapping("/user/fosterage/confirm/{id}/{applier}")
	@ResponseBody
	public String confirmFosterageCandidates(@PathVariable("id") Integer id,@PathVariable("applier") Integer applier,Model map) {
		System.out.println("get "+id+"/"+applier);
		service.confirmFosterageApplier(id, applier);
		return "confirm";
	}
	
	@RequestMapping("/user/adoption/show/{id}/{applier}")
	@ResponseBody
	public String confirmAdoptionCandidates(@PathVariable("id") Integer id,@PathVariable("applier") Integer applier,Model map) {
		System.out.println("get "+id+"/"+applier);
		service.confirmAdoptionApplier(id, applier);
		return "confirm";
	}
	
	@RequestMapping("/user/fosterage/score/{applier}/{score}")
	@ResponseBody
	public String scoreFosterage(@PathVariable("applier") Integer applier,@PathVariable("score") Integer score,Model map) {
		System.out.println("get /"+applier);
		service.scoreFosterageForApplier(applier, score);
		return "score";
	}
	
}

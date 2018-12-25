package com.stu.petc.controller;

import static org.assertj.core.api.Assertions.in;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.beans.AdoptionCandidate;
import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.FosterageCandidate;
import com.stu.petc.beans.OfferNote;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.NoteMapper;
import com.stu.petc.mapper.UserInfoMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.CheckUnreadService;
import com.stu.petc.service.UserInfoService;
import com.stu.petc.util.Encoder;
import com.stu.petc.util.Tools;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqScore;
import com.stu.petc.web.ReqTargetID;

@Controller
public class UserInfoController {

	@Autowired
	UserMapper mapper;
	@Autowired
	CheckUnreadService unreadService;
	
	@Autowired
	UserInfoService service;
	
	@RequestMapping("/user/info")
	public String userInfo(HttpServletRequest request,Model map) throws FileNotFoundException {
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
				String basepath = Tools.getImgDirectory() + "static/staticImg/forsterage/"
						+ String.valueOf(fosterNote.getId()) + "/";
				File directory = new File(basepath);
				if (directory.isDirectory()) {
					File[] files = directory.listFiles();
					for (File fileIndex : files) {
						if (fileIndex.isDirectory()) {

						} else {
							// if is file
							fosterNote.setTitleimg("/static/staticImg/forsterage/" + String.valueOf(fosterNote.getId()) + "/"
									+ fileIndex.getName());
							System.out.println(String.valueOf(fosterNote.getId()) + ":" + fosterNote.getTitleimg());
							break;
						}
					}
				}
			}
			List<AdoptionNote> adoptionList = service.getAllAdoptionByUser(id);
			for (AdoptionNote adoptionNote : adoptionList) {
				System.out.println(adoptionNote);
				String basepath = Tools.getImgDirectory() + "static/staticImg/adoption/" + String.valueOf(adoptionNote.getId()) + "/";
				File directory = new File(basepath);
				if(directory.isDirectory()){
					File []files = directory.listFiles();
					for(File fileIndex:files){
						if(fileIndex.isDirectory()){
						
						}else {
						// if is file
						adoptionNote.setTitleimg("/static/staticImg/adoption/" + String.valueOf(adoptionNote.getId()) + "/" + fileIndex.getName());
						System.out.println(String.valueOf(adoptionNote.getId()) + ":" + adoptionNote.getTitleimg());
						break;
						}
					}
				}
			}
			List<ShareNote> shareList = service.getAllShareByUser(id);
			for (ShareNote shareNote : shareList) {
				System.out.println(shareNote);
				String basepath = Tools.getImgDirectory() + "static/staticImg/share/" + String.valueOf(shareNote.getId()) + "/";
				File directory = new File(basepath);
				if(directory.isDirectory()){
					File []files = directory.listFiles();
					for(File fileIndex:files){
						if(fileIndex.isDirectory()){
						
						}else {
						// if is file
							shareNote.setTitleimg("/static/staticImg/share/" + String.valueOf(shareNote.getId()) + "/" + fileIndex.getName());
						System.out.println(String.valueOf(shareNote.getId()) + ":" + shareNote.getTitleimg());
						break;
						}
					}
				}
			}
			
			List<OfferNote> offerList = service.getAllOfferByUser(id);
			System.out.println(offerList);
			
//			map.addAllAttributes(attributeValues)
			map.addAttribute("username", username);
			int unread = unreadService.checkUnread(mapper.getUserByName(username).getUser_id());
			if (unread>0) {
				map.addAttribute("unread", unread);
			}
			map.addAttribute("id", service.getIDByName(username));
			map.addAttribute("tel", user.getUser_tel());
			map.addAttribute("fosterList", fosterList);
			map.addAttribute("shareList", shareList);
			map.addAttribute("adoptionList", adoptionList);
			map.addAttribute("offerList", offerList);
			
		}
		return "userInfo";
	}
	@RequestMapping("/user/adopt/detail/{id}")
	public String getAdoptionDetail(@PathVariable("id") Integer id, Map<String, Object> model) throws FileNotFoundException{
		AdoptionNote adoptionNote = service.getAdoptionByID(id);
		System.out.println(adoptionNote+"-"+id);
		
		String basepath = Tools.getImgDirectory() + "static/staticImg/adoption/" + String.valueOf(id) + "/";
		System.out.println(basepath);
		
		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if(directory.isDirectory()){
			File []files = directory.listFiles();
			for(File fileIndex:files){
				if(fileIndex.isDirectory()){
				
				}else {
				// if is file
				paths.add("/static/staticImg/adoption/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}
		
		System.out.println("paths:" + paths);
		
		User user = service.getUserByID(adoptionNote.getEditor());
		unreadService.setAdoptionUnreadZero(id);
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
		
		String basepath = Tools.getImgDirectory() + "static/staticImg/forsterage/" + String.valueOf(id) + "/";
		System.out.println(basepath);
		
		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if(directory.isDirectory()){
			File []files = directory.listFiles();
			for(File fileIndex:files){
				if(fileIndex.isDirectory()){
				
				}else {
				// if is file
				paths.add("/static/staticImg/forsterage/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}
		
		System.out.println("paths:" + paths);
		
		User user = service.getUserByID(fosterNote.getEditor());
		unreadService.setFosterageUnreadZero(id);
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
		
		String basepath = Tools.getImgDirectory() + "static/staticImg/share/" + String.valueOf(id) + "/";
		System.out.println(basepath);
		
		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if(directory.isDirectory()){
			File []files = directory.listFiles();
			for(File fileIndex:files){
				if(fileIndex.isDirectory()){
				
				}else {
				// if is file
				paths.add("/static/staticImg/share/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}
		
		User user = service.getUserByID(shareNote.getEditor());
		unreadService.setShareUnreadZero(id);
		model.put("publisher", user.getUsername());
		model.put("share", shareNote);
		model.put("paths", paths);
		return "userShareDetailPage";
	}
	
	
	
	
	
	
	@RequestMapping("/user/foster/delete/{id}")
	@ResponseBody
	public String deleteFosterage(@PathVariable("id") Integer id) throws FileNotFoundException {
		
		String basepath = Tools.getImgDirectory() + "static/staticImg/forsterage/" + String.valueOf(id) + "/";
		File directory = new File(basepath);
		if(directory != null && directory.isDirectory()){
			File []files = directory.listFiles();
			for(File file:files){
				file.delete();
			}
		}
		if(directory != null)directory.delete();
		
		service.deleteFosterageByID(id);
		service.deleteFosterApplyByID(id);
		
		return "SUCCESS";
	}
	@RequestMapping("/user/adoption/delete/{id}")
	@ResponseBody
	public String deleteAdoption(@PathVariable("id") Integer id) throws FileNotFoundException {
		
		String basepath = Tools.getImgDirectory() + "static/staticImg/adoption/" + String.valueOf(id) + "/";
		File directory = new File(basepath);
		if(directory != null && directory.isDirectory()){
			File []files = directory.listFiles();
			for(File file:files){
				file.delete();
			}
		}
		if(directory != null)directory.delete();
		
		service.deleteAdoptionByID(id);
		return "SUCCESS";
	}
	@RequestMapping("/user/share/delete/{id}")
	@ResponseBody
	public String deleteShare(@PathVariable("id") Integer id) throws FileNotFoundException {
		
		String basepath = Tools.getImgDirectory() + "static/staticImg/share/" + String.valueOf(id) + "/";
		File directory = new File(basepath);
		if(directory != null && directory.isDirectory()){
			File []files = directory.listFiles();
			for(File file:files){
				file.delete();
			}
		}
		if(directory != null)directory.delete();
		
		service.deleteShareByID(id);
		service.deleteShareCommentByID(id);
		service.deleteShareLikeByID(id);
		
		return "SUCCESS";
	}
	
	@RequestMapping("/user/foster/show/{id}")
	public String showFosterageCandidates(@PathVariable("id") Integer id,Model map) {
		System.out.println("get "+id);
		Integer applier = service.getFosterageActor(id);
		Integer score = service.getFosterageScore(id);
		if (score!=null) {
			map.addAttribute("ismark", 1);
			map.addAttribute("score", score.intValue());
		}else {
			map.addAttribute("ismark", 0);
		}
		if (applier!=null) {
			map.addAttribute("id", id);
			Timestamp date = null;
			User actor = mapper.getUserByID(applier);
			List<FosterageCandidate> candidates =  service.getFosterageCandidates(id);
			for (FosterageCandidate fosterageCandidate : candidates) {
				if (fosterageCandidate.getApplier().intValue()==applier) {
					date = fosterageCandidate.getApply_time();
				}
			}
			map.addAttribute("actor", actor);
			map.addAttribute("date", date);
			map.addAttribute("Times", service.getApplierTimes(applier));
			return "markPage";
		}
		System.out.println("here");
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
	public String confirmFosterageCandidates(@PathVariable("id") Integer id,@PathVariable("applier") Integer applier,Model map) {
		System.out.println("get "+id+"/"+applier);
		service.confirmFosterageApplier(id, applier);
		Integer score = service.getFosterageScore(id);
		if (score!=null) {
			map.addAttribute("ismark", 1);
			map.addAttribute("score", score.intValue());
		}else {
			map.addAttribute("ismark", 0);
		}
		map.addAttribute("id", id);
		User actor = mapper.getUserByID(applier);
		map.addAttribute("actor", actor);
		return "markPage";
	}
	
	@RequestMapping("/user/adoption/show/{id}/{applier}")
	@ResponseBody
	public String confirmAdoptionCandidates(@PathVariable("id") Integer id,@PathVariable("applier") Integer applier,Model map) {
		System.out.println("get "+id+"/"+applier);
		service.confirmAdoptionApplier(id, applier);
		return "confirm";
	}
	
	@RequestMapping("/user/fosterage/score/")
	@ResponseBody
	public LoginResponse scoreFosterage(@RequestBody ReqScore reqScore,Model map) {
		System.out.println("score");
		int fosterageid = reqScore.getId();
		int score = reqScore.getGrade();
		Integer userid = service.getFosterageActor(fosterageid);
		if (userid!=null) {
			User actor = mapper.getUserByID(userid.intValue());
			service.scoreFosterageForApplier(fosterageid,actor.getUser_id(), score);			
		}

		return new LoginResponse(0, "SUCCESS", "?");
	}
	@RequestMapping("/user/fosterage/getscore/")
	@ResponseBody
	public Integer getFosterageScore(@RequestBody ReqTargetID targetID) {
		System.out.println("score");
		int fosterageid = targetID.getTargetID();

		Integer score = service.getFosterageScore(fosterageid);
		return score;
	}
}

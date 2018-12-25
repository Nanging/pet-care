package com.stu.petc.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.AdoptionMapper;
import com.stu.petc.mapper.NoteMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.AdoptionFilerService;
import com.stu.petc.service.CheckUnreadService;
import com.stu.petc.service.UserRedisService;
import com.stu.petc.util.Tools;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqAdoptionNote;

@Controller
public class AdoptionController {
	@Autowired
	AdoptionFilerService service;
	
	@Autowired
	private UserRedisService userRedisService;
	@Autowired
	CheckUnreadService unreadService;
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/adopt/detail/{id}")
	public String getAdoptionDetail(@PathVariable("id") Integer id,Map<String, Object> model) throws FileNotFoundException {
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
		
		User user = userMapper.getUserByID(adoptionNote.getEditor());
		
		model.put("publisher", user.getUsername());
		model.put("phone", user.getUser_tel());
		model.put("adoption", adoptionNote);
		model.put("paths", paths);
		return "adoptionDetailPage";
	}
	
	
	@PostMapping("/uploadImageAdopt")
	@ResponseBody
	public int uploadFosterImage(@RequestParam("imgInput[]") MultipartFile[] files,HttpServletRequest request) throws FileNotFoundException {
		
		if(files == null) {
			System.out.println("The photos don't exist!");
			return -1;
		}
		
		int nextId = service.getMaxId() + 1;
		String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/adoption/" + String.valueOf(nextId) + "/";
        System.out.println("------------------");
		System.out.println("basePath:" + basePath);
		
		for(MultipartFile file:files) {
			File directory = new File(basePath);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        
	        try {
	        	
	            file.transferTo(new File(basePath + file.getOriginalFilename()));  // add photo to disk
	            
	        } catch (Exception e) {
	            
	        }
	        
	        System.out.println("--------------------");
			System.out.println("type:" + file.getContentType());
			System.out.println("name:" + file.getOriginalFilename());
			System.out.println("size:" + file.getSize());
		}
		
		return 1;
	}
	
	@PostMapping("/publishAdoption")
	@ResponseBody
	public LoginResponse uploadAdoptionData(@RequestBody ReqAdoptionNote reqAdoptionNote, HttpServletRequest request) {
		
		System.out.println("-----------------");
		System.out.println(reqAdoptionNote);
		
		String username="";
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
					}
				}
			}
		}
		int editor = userMapper.getUserByName(username).getUser_id();
		int nextId = service.getMaxId() + 1;
		service.addAdoption(nextId,reqAdoptionNote.getNewTitle(), editor,reqAdoptionNote.getNewKindSelect(),reqAdoptionNote.getNewRegionSelect(),reqAdoptionNote.getNewContent());
		
		return new LoginResponse(0, "success", null);
	}
	
	@GetMapping("/adoption")
	public String getData(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String regionSelect,@RequestParam(defaultValue="All") String kindSelect,@RequestParam(defaultValue="1") Integer page,HttpServletRequest request,Model map) throws FileNotFoundException {

		List<AdoptionNote> adoptionNotes = service.doFiler(searchText, regionSelect, kindSelect,page);
		System.out.println(adoptionNotes);
		
		for(AdoptionNote adoptionNote:adoptionNotes) {
			String basepath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/adoption/" + String.valueOf(adoptionNote.getId()) + "/";
			File directory = new File(basepath);
			if(directory.isDirectory()){
				File []files = directory.listFiles();
				for(File fileIndex:files){
					if(fileIndex.isDirectory()){
					
					}else {
					// if is file
					adoptionNote.setTitleimg("../staticImg/adoption/" + String.valueOf(adoptionNote.getId()) + "/" + fileIndex.getName());
					System.out.println(String.valueOf(adoptionNote.getId()) + ":" + adoptionNote.getTitleimg());
					break;
					}
				}
			}
		}
		String username=null;
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
						String currentSessionID = userRedisService.getUserSession(cookie.getValue());
						System.out.println("[currentSessionID:"+currentSessionID+"]");
						if (sessionId.equals(currentSessionID) ) {
							map.addAttribute("username", username);
							int unread = unreadService.checkUnread(userMapper.getUserByName(username).getUser_id());
							if (unread>0) {
								map.addAttribute("unread", unread);
							}
						}
					}
				}
			}
		}
		int number = service.getTotalNumber(searchText, regionSelect, kindSelect);
		int pages = number/20;
		int left = number - 20*pages;
		if (left>0) {
			pages = pages+1;
		}
		map.addAttribute("pages", pages);
		map.addAttribute("page", page);
		map.addAttribute("prev", "/adoption?searchText="+searchText+"&regionSelect="+regionSelect+"&kindSelect="+kindSelect+"&page="+(page-1));
		map.addAttribute("next", "/adoption?searchText="+searchText+"&regionSelect="+regionSelect+"&kindSelect="+kindSelect+"&page="+(page+1));
		map.addAttribute("list", adoptionNotes);
		
		Tools.getImgDirectory();
		
		return "adoptionTemplate";
	}
	
}

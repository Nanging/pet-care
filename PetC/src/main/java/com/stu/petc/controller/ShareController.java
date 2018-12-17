package com.stu.petc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
import com.stu.petc.beans.ShareCommenter;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.FosterMapper;
import com.stu.petc.mapper.ShareMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.FosterFilerService;
import com.stu.petc.service.ShareFilerService;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqAdoptionNote;
import com.stu.petc.web.ReqShareNote;

@Controller
public class ShareController {
	@Autowired
	ShareFilerService service;
	
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/share/detail/{id}")
	public String getShareDetail(@PathVariable("id") Integer id,Map<String, Object> model) throws FileNotFoundException {
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
		
		User user = userMapper.getUserByID(shareNote.getEditor());
		List<ShareCommenter> commenters = service.getShareCommenters(id);
		model.put("publisher", user.getUsername());
		model.put("share", shareNote);
		model.put("paths", paths);
		model.put("commenters", commenters);
		return "shareDetailPage";
	}
	
	@PostMapping("/uploadImageShare")
	@ResponseBody
	public int uploadImageShare(@RequestParam("imgInput[]") MultipartFile[] files,HttpServletRequest request) throws FileNotFoundException {
		
		if(files == null) {
			System.out.println("The photos don't exist!");
			return -1;
		}
		
		int nextId = service.getMaxId() + 1;
		String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/share/" + String.valueOf(nextId) + "/";
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
	
	@PostMapping("/publishShare")
	@ResponseBody
	public LoginResponse publishShareData(@RequestBody ReqShareNote reqShareNote, HttpServletRequest request) {
		
		System.out.println("-----------------");
		System.out.println(reqShareNote);
		
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
		service.addShare(nextId,reqShareNote.getNewTitle(), editor, reqShareNote.getNewKindSelect(), reqShareNote.getNewContent());
		
		return new LoginResponse(0, "success", null);
	}
	
	@GetMapping("/share")
	public String getShare(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String kindSelect,Model map) throws FileNotFoundException {
		List<ShareNote> shareNotes = service.doFiler(searchText, kindSelect);
		System.out.println(shareNotes);
		
		for(ShareNote shareNote:shareNotes) {
			String basepath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/share/" + String.valueOf(shareNote.getId()) + "/";
			File directory = new File(basepath);
			if(directory.isDirectory()){
				File []files = directory.listFiles();
				for(File fileIndex:files){
					if(fileIndex.isDirectory()){
					
					}else {
					// if is file
						shareNote.setTitleimg("../staticImg/share/" + String.valueOf(shareNote.getId()) + "/" + fileIndex.getName());
					System.out.println(String.valueOf(shareNote.getId()) + ":" + shareNote.getTitleimg());
					break;
					}
				}
			}
		}
		
		map.addAttribute("list", shareNotes);
		
		return "shareTemplate";
	}
}

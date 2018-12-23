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
import com.stu.petc.beans.ShareCommenter;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.FosterMapper;
import com.stu.petc.mapper.NoteMapper;
import com.stu.petc.mapper.ShareMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.CheckUnreadService;
import com.stu.petc.service.FosterFilerService;
import com.stu.petc.service.ShareFilerService;
import com.stu.petc.service.UserRedisService;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqAdoptionNote;
import com.stu.petc.web.ReqComment;
import com.stu.petc.web.ReqTargetID;
import com.stu.petc.web.ReqShareNote;

@Controller
public class ShareController {
	@Autowired
	ShareFilerService service;
	@Autowired
	CheckUnreadService unreadService;
	@Autowired
	private UserRedisService userRedisService;
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
		boolean like = service.checkEndorse(id, user.getUser_id());
		model.put("like", like);
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
					}
				}
			}
		}
		int editor = userMapper.getUserByName(username).getUser_id();
		int nextId = service.getMaxId() + 1;
		service.addShare(nextId,reqShareNote.getNewTitle(), editor, reqShareNote.getNewKindSelect(), reqShareNote.getNewContent());
		
		return new LoginResponse(0, "success", null);
	}
	
	
	@PostMapping("/endorse")
	@ResponseBody
	public LoginResponse publishEndorse(@RequestBody ReqTargetID reqEndorse, HttpServletRequest request) {
		
		System.out.println("-----------------");
		System.out.println(reqEndorse);
		
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
		int user_id = userMapper.getUserByName(username).getUser_id();
//		int nextId = service.getMaxId() + 1;
		int share_id = reqEndorse.getTargetID();
		ShareNote note = service.getShareByID(share_id);

		if (service.checkEndorse(share_id, user_id)) {
			service.addEndorse(share_id, user_id);
			if (user_id!=note.getEditor().intValue()) {
				service.updateShareUnread(share_id);
			}
		}

		return new LoginResponse(0, "success", null);
	}
	@PostMapping("/publishComment")
	@ResponseBody
	public LoginResponse publishComment(@RequestBody ReqComment	 reqComment, HttpServletRequest request) {
		
		System.out.println("-----------------");
		System.out.println(reqComment);
		
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
		int user_id = userMapper.getUserByName(username).getUser_id();
//		int nextId = service.getMaxId() + 1;
		int share_id = reqComment.getTargetID();
		ShareNote note = service.getShareByID(share_id);

		String comment = reqComment.getNewComment();
		service.addComment(share_id, user_id, comment);
		if (user_id!=note.getEditor().intValue()) {
			service.updateShareUnread(share_id);
		}
		return new LoginResponse(0, "success", null);
	}
	
	
	@GetMapping("/share")
	public String getShare(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String kindSelect,@RequestParam(defaultValue="1") Integer page,HttpServletRequest request,Model map) throws FileNotFoundException {
		List<ShareNote> shareNotes = service.doFiler(searchText, kindSelect,page);
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
		int number = service.getTotalNumber();
		int pages = number/20;
		int left = number/20;
		if (left>0) {
			pages = pages+1;
		}
		map.addAttribute("pages", pages);
		map.addAttribute("page", page);
		map.addAttribute("prev", "/share?searchText="+searchText+"&kindSelect="+kindSelect+"&page="+(page-1));
		map.addAttribute("next", "/share?searchText="+searchText+"&kindSelect="+kindSelect+"&page="+(page+1));
		map.addAttribute("list", shareNotes);
		
		return "shareTemplate";
	}
}

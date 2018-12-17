package com.stu.petc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.FosterMapper;
import com.stu.petc.mapper.ShareMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.FosterFilerService;
import com.stu.petc.service.ShareFilerService;

@Controller
public class ShareController {
	@Autowired
	ShareFilerService service;
	
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/share")
	public String getShare(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String kindSelect,Model map) {
		System.out.println("[searchText:"+searchText+"]"+"[searchText:"+kindSelect+"]");
		System.out.println(service.doFiler(searchText, kindSelect));
		map.addAttribute("list", service.doFiler(searchText, kindSelect));
		
		return "shareTemplate";
	}
	
	@RequestMapping("/share/detail/{id}")
	public String getShareDetail(@PathVariable("id") Integer id,Map<String, Object> model) {
		ShareNote shareNote = service.getShareByID(id);
		System.out.println(shareNote+"-"+id);
		
		User user = userMapper.getUserByID(shareNote.getEditor());
		
		model.put("publisher", user.getUsername());
		model.put("share", shareNote);
		return "shareDetailPage";
	}
}

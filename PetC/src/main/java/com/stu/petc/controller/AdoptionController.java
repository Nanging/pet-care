package com.stu.petc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

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
import com.stu.petc.beans.User;
import com.stu.petc.mapper.AdoptionMapper;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.AdoptionFilerService;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqAdoptionNote;

@Controller
public class AdoptionController {
	@Autowired
	AdoptionFilerService service;

	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/adopt/detail/{id}")
	public String getAdoptionDetail(@PathVariable("id") Integer id,Map<String, Object> model) {
		AdoptionNote adoptionNote = service.getAdoptionByID(id);
		System.out.println(adoptionNote+"-"+id);
		
		User user = userMapper.getUserByID(adoptionNote.getEditor());
		
		model.put("publisher", user.getUsername());
		model.put("phone", user.getUser_tel());
		model.put("adoption", adoptionNote);
		return "adoptionDetailPage";
	}
	
	
	@PostMapping("/uploadImageAdopt")
	@ResponseBody
	public int uploadFosterImage(@RequestParam("imgInput[]") MultipartFile[] files,HttpServletRequest request) throws FileNotFoundException {
		
		if(files == null) {
			System.out.println("The photos don't exist!");
			return -1;
		}
		
		String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/adoption/";
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
	public LoginResponse uploadAdoptionData(@RequestBody ReqAdoptionNote reqAdoptionNote) {
		
		System.out.println("-----------------");
		System.out.println(reqAdoptionNote);
		
		return new LoginResponse(0, "success", null);
	}
	
	@GetMapping("/adoption")
	public String getData(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String regionSelect,@RequestParam(defaultValue="All") String kindSelect,Model map) {

		System.out.println(service.doFiler(searchText, regionSelect, kindSelect));
		map.addAttribute("list", service.doFiler(searchText, regionSelect, kindSelect));
		return "adoptionTemplate";
	}
	
}

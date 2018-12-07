package com.stu.petc.controller;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

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
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.mapper.FosterMapper;
/**
 * post?
 * wrong position
 * @author HYX
 *
 */
import com.stu.petc.service.FosterFilerService;
import com.stu.petc.web.ReqFosterNote;
@Controller
public class FosterageController {
	@Autowired
	FosterFilerService service;
	
	@Autowired
	FosterMapper mapper;
	
	@RequestMapping("/9961")
	public String main() {
		return "fosterageTemplate";
	}
	@RequestMapping("/foster/detail/{id}")
	public String getFosterDetail(@PathVariable("id") Integer id,Map<String, Object> model) {
		FosterNote fosterNote = mapper.getFosterByID(id);
		System.out.println(fosterNote+"-"+id);
		model.put("foster", fosterNote);
		return "fosterageDetailPage";
	}
	
	
	@PostMapping("/uploadImageFoster")
	@ResponseBody
	public int uploadFosterImage(@RequestParam("imgInput[]") MultipartFile[] files,HttpServletRequest request) throws FileNotFoundException {
		
		if(files == null) {
			System.out.println("The photos don't exist!");
			return -1;
		}
		
		String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/staticImg/forsterage/";
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
	
	@PostMapping("/publishFoster")
	public String uploadFosterData(@RequestBody ReqFosterNote reqFosterNote) {
		
		System.out.println("-----------------");
		System.out.println(reqFosterNote);
		
		return "fosterageTemplate.html";
	}
	
	@GetMapping("/data")
	public String getData(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String regionSelect,@RequestParam(defaultValue="All") String kindSelect,Model map) {

		System.out.println(service.doFiler(searchText, regionSelect, kindSelect));
		map.addAttribute("list", service.doFiler(searchText, regionSelect, kindSelect));
		return "fosterageTemplate";
	}
}

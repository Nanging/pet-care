package com.stu.petc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShareController {
	
	@RequestMapping("/share")
	public String sh() {
		return "shareTemplate";
	}
}

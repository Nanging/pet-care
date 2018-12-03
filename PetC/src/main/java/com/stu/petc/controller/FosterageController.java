package com.stu.petc.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.mapper.FosterMapper;
/**
 * post?
 * wrong position
 * @author HYX
 *
 */
import com.stu.petc.service.FosterFilerService;
@Controller
public class FosterageController {
	@Autowired
	FosterFilerService service;
	@RequestMapping("/9961")
	public String main() {

		return "fosterageTemplate";
	}
	
	@GetMapping("/data")
	public String getData(@RequestParam(defaultValue="") String searchText,@RequestParam(defaultValue="All") String regionSelect,@RequestParam(defaultValue="All") String kindSelect,Model map) {

		System.out.println(service.doFiler(searchText, regionSelect, kindSelect));
		map.addAttribute("list", service.doFiler(searchText, regionSelect, kindSelect));
		return "fosterageTemplate";
	}
}

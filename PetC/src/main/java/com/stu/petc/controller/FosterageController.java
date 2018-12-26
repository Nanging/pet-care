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

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.UserMapper;
import com.stu.petc.service.CheckUnreadService;
/**
 * post?
 * wrong position
 * @author HYX
 *
 */
import com.stu.petc.service.FosterFilerService;
import com.stu.petc.service.UserRedisService;
import com.stu.petc.util.Tools;
import com.stu.petc.web.LoginResponse;
import com.stu.petc.web.ReqFosterNote;
import com.stu.petc.web.ReqTargetID;

@Controller
public class FosterageController {
	@Autowired
	FosterFilerService service;
	@Autowired
	UserMapper userMapper;
	@Autowired
	private UserRedisService userRedisService;
	@Autowired
	CheckUnreadService unreadService;
	@RequestMapping("/foster/detail/{id}")
	public String getFosterDetail(@PathVariable("id") Integer id, Map<String, Object> model,HttpServletRequest request)
			throws FileNotFoundException {
		FosterNote fosterNote = service.getFosterByID(id);
		System.out.println(fosterNote + "-" + id);

		String basepath = Tools.getImgDirectory() + "static/staticImg/forsterage/"
				+ String.valueOf(id) + "/";
		System.out.println(basepath);

		ArrayList<String> paths = new ArrayList<String>();
		File directory = new File(basepath);
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File fileIndex : files) {
				if (fileIndex.isDirectory()) {

				} else {
					// if is file
					paths.add("/static/staticImg/forsterage/" + String.valueOf(id) + "/" + fileIndex.getName());
				}
			}
		}

		System.out.println("paths:" + paths);
		String username = "";
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
		User user = userMapper.getUserByName(username);
		if (user.getUser_id().equals(fosterNote.getEditor())) {
			unreadService.setFosterageUnreadZero(id);
		}
		if (!user.getUser_id().equals(fosterNote.getEditor()) && service.checkOffer(id, user.getUser_id()) ) {
			model.put("offer", true);
		}
		User editor = userMapper.getUserByID(fosterNote.getEditor());
//		System.out.println(Tools.DateFormat(fosterNote.getPublish_date()));
		model.put("publisher", editor.getUsername());
		model.put("foster", fosterNote);
		model.put("paths", paths);
		return "fosterageDetailPage";
	}

	@PostMapping("/uploadImageFoster")
	@ResponseBody
	public int uploadFosterImage(@RequestParam("imgInput[]") MultipartFile[] files, HttpServletRequest request)
			throws FileNotFoundException {

		if (files == null) {
			System.out.println("The photos don't exist!");
			return -1;
		}

		int nextId = service.getMaxId() + 1;
		String basePath = Tools.getImgDirectory() + "static/staticImg/forsterage/"
				+ String.valueOf(nextId) + "/";
		System.out.println("------------------");
		System.out.println("basePath:" + basePath);

		for (MultipartFile file : files) {
			File directory = new File(basePath);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			try {

				file.transferTo(new File(basePath + file.getOriginalFilename())); // add photo to disk

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
	@ResponseBody
	public LoginResponse uploadFosterData(@RequestBody ReqFosterNote reqFosterNote, HttpServletRequest request) {

		System.out.println("-----------------");
		System.out.println(reqFosterNote);

		String username = "";
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
		service.addFoster(nextId, reqFosterNote.getNewTitle(), editor, reqFosterNote.getNewKindSelect(),
				reqFosterNote.getNewRegionSelect(), reqFosterNote.getNewContent());

		return new LoginResponse(0, "success", null);
	}

	@GetMapping("/fosterage")
	public String getFosterage(@RequestParam(defaultValue = "") String searchText,
			@RequestParam(defaultValue = "All") String regionSelect,
			@RequestParam(defaultValue = "All") String kindSelect, @RequestParam(defaultValue = "1") Integer page,
			HttpServletRequest request, Model map) throws FileNotFoundException {

		List<FosterNote> fosterNotes = service.doFiler(searchText, regionSelect, kindSelect, page);
		System.out.println(fosterNotes);

		for (FosterNote fosterNote : fosterNotes) {
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
		String username = null;
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
						System.out.println("[currentSessionID:" + currentSessionID + "]");
						if (sessionId.equals(currentSessionID)) {
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
		int pages = number / 20;
		int left = number - 20*pages;
		if (left > 0) {
			pages = pages + 1;
		}
		System.out.println(number);
		System.out.println(pages);
		map.addAttribute("pages", pages);
		map.addAttribute("page", page);
		map.addAttribute("prev", "/fosterage?searchText=" + searchText + "&regionSelect=" + regionSelect
				+ "&kindSelect=" + kindSelect + "&page=" + (page - 1));
		map.addAttribute("next", "/fosterage?searchText=" + searchText + "&regionSelect=" + regionSelect
				+ "&kindSelect=" + kindSelect + "&page=" + (page + 1));
		map.addAttribute("list", fosterNotes);

		return "fosterageTemplate";
	}

	@PostMapping("/offer")
	@ResponseBody
	public LoginResponse publishEndorse(@RequestBody ReqTargetID reqOffer, HttpServletRequest request) {

		System.out.println("-----------------");
		System.out.println(reqOffer);

		String username = "";
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
		int foserage_id = reqOffer.getTargetID();
		FosterNote note = service.getFosterByID(foserage_id);

		if (service.checkOffer(foserage_id, user_id)) {
			service.addOffer(foserage_id, user_id);
			if (user_id != note.getEditor().intValue()) {
				service.updateFosterageUnread(foserage_id);
			}
		}

		return new LoginResponse(0, "success", null);
	}
}

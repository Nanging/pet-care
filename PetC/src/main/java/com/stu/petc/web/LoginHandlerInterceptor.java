package com.stu.petc.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.service.UserRedisService;
import com.stu.petc.util.Encoder;

//@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private UserRedisService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("GET")) {
			
		}
		boolean flag = false;
		String username = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						flag = true;
						/**
						 * check user
						 */
						String[] token = cookie.getValue().split("_");
						username = token[0];
						password = Encoder.decryptBasedDes(token[1]);
					}
				}
			}
		}
		System.out.println("[flag:"+flag+"]");
		if (flag==true) {
			if (null!=request.getSession().getAttribute("userStatus")) {
				System.out.println("login to login : "+request.getSession().getAttribute("userStatus"));
			}else {
				request.getSession().setAttribute("userStatus", "SAVED");
				System.out.println("login to login : SAVED");
			}
			return true;
		}
		request.getSession().setAttribute("userStatus", "DEFAULT");
		if (request.getCookies()==null) {
			System.out.println("[handler : EMPTY]");
		}
//		request.getCookies();
		System.out.println("login to login : DEFAULT");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}

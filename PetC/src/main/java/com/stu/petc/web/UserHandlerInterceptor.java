package com.stu.petc.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.stu.petc.service.UserRedisService;

public class UserHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private UserRedisService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		boolean isExists = false;
		boolean flag = false;
		Cookie[] cookies = request.getCookies();
		if (null!=cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginStatus")) {
					if (null != cookie.getValue() && !"".equals(cookie.getValue())) {
						isExists = true;
						flag = true;
						/**
						 * check user
						 */
						HttpSession session = request.getSession();
						String sessionId = session.getId();
						String currentSessionID = service.getUserSession(cookie.getValue());
						System.out.println("[currentSessionID:"+currentSessionID+"]");
						if (!currentSessionID.equals(sessionId) ) {
							flag = false;
						}
					}
				}
			}		
		}
		System.out.println("[isExists:"+isExists+"]"+"[flag:"+flag+"]");
		if (isExists == true) {
			if (flag == true) {
				request.getSession().setAttribute("userStatus", "SAVED");
				System.out.println("main to main : SUCCESS");
				return true;
			} else {
				request.getSession().setAttribute("userStatus", "FAILED");
				System.out.println("main to login : FAILED");
				response.sendRedirect("/login");
				return false;
			}
		}
		request.getSession().setAttribute("userStatus", "NUKNOWN");

		System.out.println("main to login : NUKNOWN");
		response.sendRedirect("/login");
		return false;
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

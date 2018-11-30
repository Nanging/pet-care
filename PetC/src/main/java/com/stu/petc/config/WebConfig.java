package com.stu.petc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stu.petc.web.LoginHandlerInterceptor;
import com.stu.petc.web.UserHandlerInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean LoginHandlerInterceptor createLoginHandlerInterceptor() {
		
		return new LoginHandlerInterceptor();
	}
	@Bean UserHandlerInterceptor createUserHandlerInterceptor() {
		
		return new UserHandlerInterceptor();
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addRedirectViewController("/", "/main");
	}


/**
 * 拦截器的添加顺序就是执行顺序
 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(createLoginHandlerInterceptor()).addPathPatterns("/login");
		registry.addInterceptor(createUserHandlerInterceptor()).addPathPatterns("/main");
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}

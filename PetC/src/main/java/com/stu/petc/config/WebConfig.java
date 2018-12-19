package com.stu.petc.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stu.petc.web.LoginHandlerInterceptor;
import com.stu.petc.web.UserHandlerInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
                ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
                factory.addErrorPages(error404,error500);
            }
        };
    }

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
		registry.addInterceptor(createUserHandlerInterceptor()).addPathPatterns(new String[] {"/main","/foster/**","/share/**","/user/**"}).excludePathPatterns("/login");
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}

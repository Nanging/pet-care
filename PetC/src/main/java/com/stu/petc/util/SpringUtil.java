package com.stu.petc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext= null;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		if (SpringUtil.applicationContext== null) {
			SpringUtil.applicationContext = applicationContext;
		}
	}
	public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> cls){
        return getApplicationContext().getBean(cls);
    }
    
    public static <T> T getBean(String name,Class<T> cls){
        return getApplicationContext().getBean(name, cls);
    }


}

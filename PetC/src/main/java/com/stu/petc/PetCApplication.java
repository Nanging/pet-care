package com.stu.petc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.stu.petc.mapper.FosterMapper;
import com.stu.petc.util.Encoder;



@SpringBootApplication
public class PetCApplication {

	
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(PetCApplication.class, args);
		
//		System.out.println("[Bob]:"+Encoder.encryptBasedDes("123"));
//		System.out.println("[Ken]:"+Encoder.encryptBasedDes("123"));
//		System.out.println("[John]:"+Encoder.encryptBasedDes("798"));
//		System.out.println("[Jame]:"+Encoder.encryptBasedDes("abc"));
//		System.out.println("[ZhaoWei]:"+Encoder.encryptBasedDes("123"));
//		System.out.println("[Zhangsan]:"+Encoder.encryptBasedDes("1234"));
//		System.out.println("[Yangguangda]:"+Encoder.encryptBasedDes("123456"));
//		System.out.println("[Lisi]:"+Encoder.encryptBasedDes("lisi123"));
//		System.out.println("[Wangwu]:"+Encoder.encryptBasedDes("helloworld"));
//		System.out.println("[Tom]:"+Encoder.encryptBasedDes("tomcat"));
//		System.out.println(Encoder.decryptBasedDes(Encoder.encryptBasedDes("123")));	
//		context.close();
	}
}

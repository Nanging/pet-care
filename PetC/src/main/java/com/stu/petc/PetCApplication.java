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
		
//		System.out.println(Encoder.encryptBasedDes("123"));
//		System.out.println(Encoder.decryptBasedDes(Encoder.encryptBasedDes("123")));
//		FosterMapper mapper = new F
//		
//		context.close();
	}
}

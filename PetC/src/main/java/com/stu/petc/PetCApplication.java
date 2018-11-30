package com.stu.petc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication
public class PetCApplication {

	public static void main(String[] args) {

		SpringApplication.run(PetCApplication.class, args);
	}
}

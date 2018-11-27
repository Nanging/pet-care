package com.stu.petc.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2497196479043014290L;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

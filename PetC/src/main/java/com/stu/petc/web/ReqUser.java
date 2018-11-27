package com.stu.petc.web;

import javax.validation.constraints.NotBlank;

public class ReqUser {
	private String username;
	private String password;
	private String phone;
	
	@Override
	public String toString() {
		return "ReqUser [username=" + username + ", password=" + password + ", phone=" + phone + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
	
}

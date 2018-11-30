package com.stu.petc.web;

import javax.validation.constraints.NotBlank;

public class ReqUser {
	private String username;
	private String password;
	private String phone;
	
	public boolean signInCheck() {
		return null!=username&&null!=password&&username.length()<=16&&password.length()<=16;
	}
	public boolean signUpCheck() {
		
		return true;
	}
	@Override
	public String toString() {
		return "ReqUser [username=" + username + ", password=" + password + ", phone=" + phone + "]";
	}
	@NotBlank(message="username cant be empty")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotBlank(message="password cant be empty")
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

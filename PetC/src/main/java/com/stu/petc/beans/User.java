package com.stu.petc.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private Integer user_id;
	private String username;
	private String password;
	private String user_tel;
	private String user_role;
	private String user_loc;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonIgnore
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	@JsonIgnore
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	@JsonIgnore
	public String getUser_loc() {
		return user_loc;
	}
	public void setUser_loc(String user_loc) {
		this.user_loc = user_loc;
	}
	
	
	
	
	
}

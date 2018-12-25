package com.stu.petc.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer user_id;
	private String username;
	private String password;
	private String user_tel;
	private String user_role;
	private Float user_score;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public Float getUser_score() {
		return user_score;
	}
	public void setUser_score(Float user_score) {
		this.user_score = user_score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", user_tel="
				+ user_tel + ", user_role=" + user_role + ", user_score=" + user_score + "]";
	}
	
	
	
	
	
}

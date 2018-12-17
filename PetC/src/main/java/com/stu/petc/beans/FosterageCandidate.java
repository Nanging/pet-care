package com.stu.petc.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class FosterageCandidate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer applier;
	private String username;
	private String user_tel;
	private Timestamp apply_time;
	public Integer getApplier() {
		return applier;
	}
	public void setApplier(Integer applier) {
		this.applier = applier;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public Timestamp getApply_time() {
		return apply_time;
	}
	public void setApply_time(Timestamp apply_time) {
		this.apply_time = apply_time;
	}
	@Override
	public String toString() {
		return "FosterageCandidate [applier=" + applier + ", username=" + username + ", user_tel=" + user_tel
				+ ", apply_time=" + apply_time + "]";
	}
	
	
}

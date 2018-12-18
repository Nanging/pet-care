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
	private Integer user_score;
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
	public Integer getUser_score() {
		return user_score;
	}
	public void setUser_score(Integer user_score) {
		this.user_score = user_score;
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
				+ ", user_score=" + user_score + ", apply_time=" + apply_time + "]";
	}
	
	
}

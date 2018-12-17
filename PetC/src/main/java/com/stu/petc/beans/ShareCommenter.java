package com.stu.petc.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShareCommenter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer commenter; 
	private	String username;
	private String comment;
	private Timestamp comment_date;
	public Integer getCommenter() {
		return commenter;
	}
	public void setCommenter(Integer commenter) {
		this.commenter = commenter;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getComment_date() {
		return comment_date;
	}
	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
	@Override
	public String toString() {
		return "ShareCommenter [commenter=" + commenter + ", username=" + username + ", comment=" + comment
				+ ", comment_date=" + comment_date + "]";
	}
	
	
}

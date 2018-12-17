package com.stu.petc.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ShareNote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Integer editor;
	private String type;
	private String content;
	private Timestamp publish_date;
	private Integer likes;
	private Integer comments;
	private String titleimg;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getEditor() {
		return editor;
	}
	public void setEditor(Integer editor) {
		this.editor = editor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Timestamp publish_date) {
		this.publish_date = publish_date;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getTitleimg() {
		return titleimg;
	}
	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}
	@Override
	public String toString() {
		return "ShareNote [id=" + id + ", title=" + title + ", editor=" + editor + ", type=" + type + ", content="
				+ content + ", publish_date=" + publish_date + ", likes=" + likes + ", comments=" + comments
				+ ", titleimg=" + titleimg + "]";
	}

	
	
}

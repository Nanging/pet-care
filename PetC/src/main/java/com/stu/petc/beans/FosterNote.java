package com.stu.petc.beans;

import java.io.Serializable;

public class FosterNote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2607544457360783004L;
	private Integer id;
	private String title;
	private Integer editor;
	private String type;
	private String location;
	private String text;
	private Integer actor;
	private Integer state;
	
	
	@Override
	public String toString() {
		return "FosterNote [id=" + id + ", title=" + title + ", editor=" + editor + ", type=" + type + ", location="
				+ location + ", text=" + text + ", actor=" + actor + ", state=" + state + "]";
	}
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getActor() {
		return actor;
	}
	public void setActor(Integer actor) {
		this.actor = actor;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}

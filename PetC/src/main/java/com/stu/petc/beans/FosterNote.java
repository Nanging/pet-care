package com.stu.petc.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class FosterNote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Integer editor;
	private String type;
	private String location;
	private String text;
	private Integer actor;
	private Integer state;
	private Timestamp publish_date;
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
	public Timestamp getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Timestamp publish_date) {
		this.publish_date = publish_date;
	}
	@Override
	public String toString() {
		return "FosterNote [id=" + id + ", title=" + title + ", editor=" + editor + ", type=" + type + ", location="
				+ location + ", text=" + text + ", actor=" + actor + ", state=" + state + ", publish_date="
				+ publish_date + ", getId()=" + getId() + ", getTitle()=" + getTitle() + ", getEditor()=" + getEditor()
				+ ", getType()=" + getType() + ", getLocation()=" + getLocation() + ", getText()=" + getText()
				+ ", getActor()=" + getActor() + ", getState()=" + getState() + ", getPublish_date()="
				+ getPublish_date() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


	
	
}

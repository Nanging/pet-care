package com.stu.petc.beans;

public class OfferNote {
	private Integer id;
	private String title;
	private Integer editor;
	private Integer actor;
	private Integer score;
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
	public Integer getActor() {
		return actor;
	}
	public void setActor(Integer actor) {
		this.actor = actor;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "OfferNote [id=" + id + ", title=" + title + ", editor=" + editor + ", actor=" + actor + ", score="
				+ score + "]";
	}
	
}

package com.stu.petc.web;

public class ReqComment {
	private Integer targetID;
	private String newComment;
	public Integer getTargetID() {
		return targetID;
	}
	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}
	public String getNewComment() {
		return newComment;
	}
	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}
	@Override
	public String toString() {
		return "ReqComment [targetID=" + targetID + ", newComment=" + newComment + "]";
	}
	
}

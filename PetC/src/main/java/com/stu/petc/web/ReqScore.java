package com.stu.petc.web;

public class ReqScore {
	private Integer grade;
	private Integer id;
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ReqScore [grade=" + grade + ", id=" + id + "]";
	}
	
}

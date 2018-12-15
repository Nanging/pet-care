package com.stu.petc.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class FosterageCandidate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer applier;
	private Timestamp apply_time;
	public Integer getApplier() {
		return applier;
	}
	public void setApplier(Integer applier) {
		this.applier = applier;
	}
	public Timestamp getApply_time() {
		return apply_time;
	}
	public void setApply_time(Timestamp apply_time) {
		this.apply_time = apply_time;
	}
	@Override
	public String toString() {
		return "FosterageCandidate [applier=" + applier + ", apply_time=" + apply_time + "]";
	}
	
}

package com.stu.petc.web;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReqFosterNote {
	
	private MultipartFile[] images;
	private String newTitle;
	private String newRegionSelect;
	private String newKindSelect;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date newDate;
	
	private String newContent;
	
	public String getNewTitle() {
		return newTitle;
	}
	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}
	public String getNewRegionSelect() {
		return newRegionSelect;
	}
	public void setNewRegionSelect(String newRegionSelect) {
		this.newRegionSelect = newRegionSelect;
	}
	public String getNewKindSelect() {
		return newKindSelect;
	}
	public void setNewKindSelect(String newKindSelect) {
		this.newKindSelect = newKindSelect;
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	public MultipartFile[] getImages() {
		return images;
	}
	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "ReqFosterNote [images=" + Arrays.toString(images) + ", newTitle=" + newTitle + ", newRegionSelect="
				+ newRegionSelect + ", newKindSelect=" + newKindSelect + ", newDate=" + newDate + ", newContent="
				+ newContent + "]";
	}
	
	
	
}

package com.stu.petc.web;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class ReqAdoptionNote {

	private MultipartFile[] images;
	private String newTitle;
	private String newRegionSelect;
	private String newKindSelect;
	private String newContent;
	public MultipartFile[] getImages() {
		return images;
	}
	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
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
	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	@Override
	public String toString() {
		return "ReqAdoptionNote [images=" + Arrays.toString(images) + ", newTitle=" + newTitle + ", newRegionSelect="
				+ newRegionSelect + ", newKindSelect=" + newKindSelect + ", newContent=" + newContent + "]";
	}
	
}

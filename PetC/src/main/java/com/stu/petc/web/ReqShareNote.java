package com.stu.petc.web;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class ReqShareNote {
	private MultipartFile[] images;
	private String newTitle;
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
		return "ReqShareNote [images=" + Arrays.toString(images) + ", newTitle=" + newTitle + ", newKindSelect="
				+ newKindSelect + ", newContent=" + newContent + "]";
	}
}

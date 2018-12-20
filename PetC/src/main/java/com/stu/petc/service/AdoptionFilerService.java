package com.stu.petc.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.mapper.AdoptionMapper;

@Service
public class AdoptionFilerService {
	
	@Autowired
	AdoptionMapper mapper;
	public List<AdoptionNote> doFiler(String searchText,String regionSelect,String kindSelect,Integer page) {
		List<AdoptionNote> rawList = mapper.getAdoption(searchText, regionSelect, kindSelect,page);
		return rawList;
	}
	public AdoptionNote getAdoptionByID(Integer id) {

		return mapper.getAdoptionByID(id);
	}
	
	public Integer getMaxId() {
		return mapper.getMaxId();
	}
	
	public void addAdoption(Integer id, String title, Integer editor, String type, String location, String text) {
		mapper.addAdoption(id, title, editor, type, location, text);
	}
	

	public Integer getTotalNumber() {
		return mapper.getTotalNumber();
	}
}

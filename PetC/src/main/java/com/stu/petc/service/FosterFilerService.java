package com.stu.petc.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.mapper.FosterMapper;


@Service
public class FosterFilerService {
	
	@Autowired
	FosterMapper mapper;
	public List<FosterNote> doFiler(String searchText,String regionSelect,String kindSelect,Integer page) {
		List<FosterNote> rawList = mapper.getFoster(searchText,regionSelect, kindSelect,page);
		return rawList;
	}
	public FosterNote getFosterByID(Integer id) {
		return mapper.getFosterByID(id);
	}
	
	public Integer getMaxId() {
		return mapper.getMaxId();
	}
	
	public void addFoster(Integer id, String title, Integer editor, String type, String location, String text) {
		mapper.addFoster(id, title, editor, type, location, text);
	}
	public Integer updateFosterageUnread(Integer id) {
		return mapper.updateFosterageUnread(id);
	}
	public boolean checkOffer(Integer id,Integer user_id) {
		if (mapper.checkOffer(id, user_id)>0) {
			return false;
		}
		return true;
	}
	public Integer addOffer(Integer id,Integer user_id) {
		return mapper.addOffer(id, user_id);
	}
	public Integer getTotalNumber() {
		return mapper.getTotalNumber();
	}
}

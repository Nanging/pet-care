package com.stu.petc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.mapper.AdoptionMapper;

@Service
public class AdoptionFilerService {
	
	@Autowired
	AdoptionMapper mapper;
	public List<AdoptionNote> doFiler(String searchText,String regionSelect,String kindSelect) {
		List<AdoptionNote> rawList = mapper.getAdoption(searchText, regionSelect, kindSelect);
		return rawList;
	}
}

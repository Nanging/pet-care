package com.stu.petc.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.mapper.FosterMapper;


@Service
public class FosterFilerService {
	
	@Autowired
	FosterMapper mapper;
	public List<FosterNote> doFiler(String searchText,String regionSelect,String kindSelect) {
		List<FosterNote> rawList = mapper.getFoster(searchText,regionSelect, kindSelect);
		return rawList;
	}
}

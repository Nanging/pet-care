package com.stu.petc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.mapper.AdoptionMapper;
import com.stu.petc.mapper.FosterMapper;
import com.stu.petc.mapper.ShareMapper;
import com.stu.petc.mapper.UserInfoMapper;

@Service
public class UserInfoService {
	@Autowired
	FosterMapper fosterMapper;
	@Autowired
	ShareMapper shareMapper;
	@Autowired
	AdoptionMapper adoptionMapper;
	@Autowired
	UserInfoMapper userInfoMapper;
	public List<FosterNote> getFosterageList() {
		return null;
	}
	
	public List<FosterNote> getShareList() {
		return null;
	}
	public List<FosterNote> getAdoptionList() {
		return null;
	}

	public int getIDByName(String username) {
		// TODO Auto-generated method stub
		return userInfoMapper.getIDByName(username);
	}

	public List<FosterNote> getAllFosterByUser(int id) {
		// TODO Auto-generated method stub
		return userInfoMapper.getAllFosterByUser(id);
	}

	public List<AdoptionNote> getAllAdoptionByUser(int id) {
		// TODO Auto-generated method stub
		return userInfoMapper.getAllAdoptionByUser(id);
	}

	public List<ShareNote> getAllShareByUser(int id) {
		// TODO Auto-generated method stub
		return userInfoMapper.getAllShareByUser(id);
	}
	
	public Integer deleteFosterageByID(int id) {
		return fosterMapper.deleteFosterByID(id);
	}
	public Integer deleteAdoptionByID(int id) {
		return adoptionMapper.deleteAdoptionByID(id);
	}
	public Integer deleteShareByID(int id) {
		return shareMapper.deleteShareByID(id);
	}
	
}

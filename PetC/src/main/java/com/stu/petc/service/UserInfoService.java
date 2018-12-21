package com.stu.petc.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.AdoptionCandidate;
import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.FosterageCandidate;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;
import com.stu.petc.mapper.AdoptionMapper;
import com.stu.petc.mapper.FosterMapper;
import com.stu.petc.mapper.ShareMapper;
import com.stu.petc.mapper.UserInfoMapper;
import com.stu.petc.mapper.UserMapper;

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
	@Autowired
	UserMapper userMapper;
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

	public List<FosterageCandidate> getFosterageCandidates(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.getFosterageCandidates(id);
	}
	
	public List<AdoptionCandidate> getAdoptionCandidates(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.getAdoptionCandidates(id);
	}
	
	public AdoptionNote getAdoptionByID(Integer id) {
		return adoptionMapper.getAdoptionByID(id);
	}
	
	public FosterNote getFosterByID(Integer id) {
		return fosterMapper.getFosterByID(id);
	}
	
	public ShareNote getShareByID(Integer id) {
		return shareMapper.getShareByID(id);
	}
	
	public User getUserByID(Integer id) {
		return userMapper.getUserByID(id);
	}
	
	public Integer confirmFosterageApplier(Integer fosterageid, Integer applier) {
		return userInfoMapper.confirmFosterageApplier(fosterageid, applier);
	}
	
	
	public Integer confirmAdoptionApplier(Integer adoptionid, Integer applier) {
		return userInfoMapper.confirmAdoptionApplier(adoptionid, applier);
	}
	public Integer scoreFosterageForApplier(Integer fosterageid,Integer applier, Integer score) {
		userInfoMapper.scoreFosterage(fosterageid, score);
		return userInfoMapper.scoreFosterageForApplier(applier,score);
	}
	public Integer setFosterageUnreadZero(Integer id) {
		return userInfoMapper.setFosterageUnreadZero(id);
	}
	public Integer setAdoptionUnreadZero(Integer id) {
		return userInfoMapper.setAdoptionUnreadZero(id);
	}
	public Integer setShareUnreadZero(Integer id) {
		return userInfoMapper.setShareUnreadZero(id);
	}
	public Integer getFosterageActor(Integer fosterageid) {
		return userInfoMapper.getFosterageActor(fosterageid);
	}
	public Integer getFosterageScore(Integer fosterageid) {
		return userInfoMapper.getFosterageScore(fosterageid);
	}
}

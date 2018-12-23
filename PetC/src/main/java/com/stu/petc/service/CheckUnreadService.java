package com.stu.petc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.mapper.NoteMapper;

@Service
public class CheckUnreadService {
	@Autowired
	private NoteMapper mapper;
	
	public int checkUnread(Integer id) {
		
		int fosterage = 0;
		int share = 0;
		int adoption = 0;
		if (mapper.getFosterageUnread(id)!=null) {
			fosterage = mapper.getFosterageUnread(id).intValue();
		}
		if (mapper.getShareUnread(id)!=null) {
			share = mapper.getShareUnread(id).intValue();
		}
		if (mapper.getAdoptionUnread(id)!=null) {
			adoption = mapper.getAdoptionUnread(id).intValue();
		}
		return adoption+fosterage+share;
	}
	public Integer setFosterageUnreadZero(Integer id) {
	return mapper.setFosterageUnreadZero(id);
	}
	public Integer setAdoptionUnreadZero(Integer id) {
		return mapper.setAdoptionUnreadZero(id);
	}
	public Integer setShareUnreadZero(Integer id) {
	return mapper.setShareUnreadZero(id);
	}	
}

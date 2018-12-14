package com.stu.petc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.mapper.ShareMapper;
@Service
public class ShareFilerService {
	@Autowired
	ShareMapper mapper;
	public List<ShareNote> doFiler(String searchText,String kindSelect) {
		List<ShareNote> rawList = mapper.getShare(searchText, kindSelect);
		if (rawList!=null&&rawList.size()>0) {
			for (int i = 0; i < rawList.size(); i++) {
				ShareNote sn= rawList.get(i);
				sn.setLikes(mapper.getLikesNumber(sn.getId()));
				sn.setComments(mapper.getCommentsNumber(sn.getId()));
			}
		}
		return rawList;
	}
	public ShareNote getShareByID(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getShareByID(id);
	}
}

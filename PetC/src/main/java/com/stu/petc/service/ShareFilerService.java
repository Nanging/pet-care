package com.stu.petc.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareCommenter;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.mapper.ShareMapper;
@Service
public class ShareFilerService {
	@Autowired
	ShareMapper mapper;
	public List<ShareNote> doFiler(String searchText,String kindSelect,Integer page) {
		List<ShareNote> rawList = mapper.getShare(searchText, kindSelect,page);
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
	
	public Integer getMaxId() {
		if(mapper.getTotal()>0)
			return mapper.getMaxId();
		else
			return 0;
	}
	
	public void addShare(Integer id, String title, Integer editor, String type, String content) {
		mapper.addShare(id, title, editor, type, content);
	}
	public List<ShareCommenter> getShareCommenters(Integer shareid){
		return mapper.getShareCommenters(shareid);
	}
	public void	 addComment(Integer share_id, Integer user_id, String comment) {
		mapper.addComment(share_id, user_id, comment);
		mapper.updateComment(share_id);
	}
	public void addEndorse(Integer share_id, Integer user_id) {
		mapper.updateLike(share_id);
		mapper.addLike(share_id, user_id);
	}
	public boolean checkEndorse(Integer share_id, Integer user_id) {
		if (mapper.checkLike(share_id, user_id)==0) {
			return true;
		}
		return false;
	}

	public Integer updateShareUnread(Integer id) {
		return mapper.updateShareUnread(id);
	}
	
	public Integer getTotalNumber(String searchText, String kindSelect) {
		return mapper.getTotalNumber(searchText, kindSelect);
	}
}

package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.stu.petc.beans.AdoptionCandidate;
import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.FosterageCandidate;
import com.stu.petc.beans.ShareCommenter;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.beans.User;

@Mapper
public interface UserInfoMapper {

	@Select("select * from foster where editor= #{useid} order by state")
	public List<FosterNote> getAllFosterByUser(Integer useid);
	
	@Select("select applier,apply_time from foster_apply where foster_id = #{fosterageid} )")
	public List<FosterageCandidate> getFosterageCandidates(Integer fosterageid);
	
	@Select("select * from foster where id in ("
			+ "select foster_id from foster_apply where applier = #{useid} )"
			+ " order by state")
	public List<FosterNote> getAllAppliedFosterByUser(Integer useid);
	
	
	@Select("select * from adoption where editor= #{useid} order by state")
	public List<AdoptionNote> getAllAdoptionByUser(Integer useid);
	
	@Select("select applier,apply_time from adoption_apply where adoption_id = #{adoptionid} )")
	public List<AdoptionCandidate> getAdoptionCandidates(Integer adoptionid);
	
	@Select("select * from adoption where id in ("
			+ "select adoption_id from adoption_apply where applier = #{useid} )"
			+ " order by state")
	public List<AdoptionNote> getAllAppliedAdoptionByUser(Integer useid);	
	
	
	@Select("select * from share where editor= #{useid}")
	public List<ShareNote> getAllShareByUser(Integer useid);

	@Select("select commenter,comment,comment_date from share_comment where share_id = #{shareid} )")
	public List<ShareCommenter> getShareCommenters(Integer shareid);
	
	@Select("select * from share where id in ("
			+ "select share_id from share_comment where commenter = #{useid} )")
	public List<ShareNote> getAllCommentedShareByUser(Integer useid);
	
	
	@Select("select user_id from user where username = #{username}")
	public Integer getIDByName(String username);
	
	
}

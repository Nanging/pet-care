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
	
	@Select("select foster_apply.applier,user.username,user.user_tel,foster_apply.apply_time "
			+ "from foster_apply, user "
			+ "where foster_apply.foster_id = #{fosterageid} and user.user_id = foster_apply.applier ")
	public List<FosterageCandidate> getFosterageCandidates(Integer fosterageid);
	
	@Select("select * from foster where id in ("
			+ "select foster_id from foster_apply where applier = #{useid} )"
			+ " order by state")
	public List<FosterNote> getAllAppliedFosterByUser(Integer useid);
	
	
	@Select("select * from adoption where editor= #{useid} order by state")
	public List<AdoptionNote> getAllAdoptionByUser(Integer useid);
	
	@Select("select adoption_apply.applier,user.username,user.user_tel,adoption_apply.apply_time "
			+ "from adoption_apply, user "
			+ "where adoption_apply.adoption_id = #{adoptionid} and user.user_id = adoption_apply.applier ")
	public List<AdoptionCandidate> getAdoptionCandidates(Integer adoptionid);
	
	@Select("select * from adoption where id in ("
			+ "select adoption_id from adoption_apply where applier = #{useid} )"
			+ " order by state")
	public List<AdoptionNote> getAllAppliedAdoptionByUser(Integer useid);	
	
	
	@Select("select * from share where editor= #{useid}")
	public List<ShareNote> getAllShareByUser(Integer useid);

	@Select("select share_comment.commenter,user.username,share_comment.comment,share_comment.comment_date "
			+ "from share_comment, user "
			+ "where share_comment.share_id = #{shareid} and user.user_id = share_comment.commenter ")
	public List<ShareCommenter> getShareCommenters(Integer shareid);
	
	@Select("select * from share where id in ("
			+ "select share_id from share_comment where commenter = #{useid} )")
	public List<ShareNote> getAllCommentedShareByUser(Integer useid);
	
	
	@Select("select user_id from user where username = #{username}")
	public Integer getIDByName(String username);
	
	
}

package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.User;

@Mapper
public interface UserInfoMapper {

	@Select("select * from foster where editor= #{useid} order by state")
	public List<FosterNote> getAllFosterByUser(Integer useid);
	
	@Select("select * from user where user_id in ("
			+ "select applier from foster_apply where foster_id = #{fosterageid} )"
			+ " order by state")
	public List<User> getFosterageCandidates(Integer fosterageid);
	
	@Select("select * from foster where id in ("
			+ "select foster_id from foster_apply where applier = #{useid} )"
			+ " order by state")
	public List<FosterNote> getAllAppliedFosterByUser(Integer useid);
	
	
	@Select("select * from adoption where editor= #{useid} order by state")
	public List<FosterNote> getAllAdoptionByUser(Integer useid);
	
	@Select("select * from adoption where id in ("
			+ "select adoption_id from adoption_apply where applier = #{useid} )"
			+ " order by state")
	public List<FosterNote> getAllAppliedAdoptionByUser(Integer useid);	
	
	
	@Select("select user_id from user where username = #{username}")
	public Integer getIDByName(String username);
	
	
}

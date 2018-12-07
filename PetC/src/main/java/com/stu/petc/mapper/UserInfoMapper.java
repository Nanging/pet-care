package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.stu.petc.beans.FosterNote;

@Mapper
public interface UserInfoMapper {

	@Select("select * from foster where editor= #{useid} order by state")
	public List<FosterNote> getAllFosterByUser(Integer useid);
	
	
	@Select("select user_id from user where username = #{username}")
	public Integer getIDByName(String username);
	
	
}

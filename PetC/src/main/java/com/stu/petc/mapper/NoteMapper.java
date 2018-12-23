package com.stu.petc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {
	@Select("select sum(unread) from foster where editor =#{id}")
	public Integer getFosterageUnread(Integer id);
	
	@Select("select sum(unread) from foster where editor =#{id}")
	public Integer getAdoptionUnread(Integer id);
	
	@Select("select sum(unread) from foster where editor =#{id}")
	public Integer getShareUnread(Integer id);
	
	@Update("update foster set unread = 0 where id =#{id}")
	public Integer setFosterageUnreadZero(Integer id);
	
	
	@Update("update adoption set unread = 0 where id =#{id}")
	public Integer setAdoptionUnreadZero(Integer id);
	
	
	@Update("update share set unread = 0 where id =#{id}")
	public Integer setShareUnreadZero(Integer id);
}

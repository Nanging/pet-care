package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.util.SqlProvider;

@Mapper
public interface ShareMapper {
	@Insert("insert into share (title,editor,type,content)values(#{title},#{editor},#{type},#{content})")
	public Integer addShare(ShareNote newShare);
	
	@Delete("delete from share where id=#{arg1}")
	public Integer deleteShareByID(Integer id);
	

	@Select("select * from share where id = #{id}")
	public ShareNote getShareByID(Integer id);
	
	@Select("select * from share limit #{begin}, #{end}")
	public List<ShareNote> getShareList(Integer begin, Integer end);
	
	@Select("select title from share limit #{begin}, #{end}")
	public List<String> getTitleList(Integer begin, Integer end);
	

	
	@SelectProvider(type=SqlProvider.class,method="getShare")
	public List<ShareNote> getShare(String title,String type);
	
	@Select("select count(*) from share_like where share_id = #{id}")
	public Integer getLikesNumber(Integer id);
	
	@Select("select count(*) from share_comment where share_id = #{id}")
	public Integer getCommentsNumber(Integer id);
}

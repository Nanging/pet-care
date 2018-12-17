package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.stu.petc.beans.AdoptionNote;
import com.stu.petc.util.SqlProvider;

@Mapper
public interface AdoptionMapper {
//	@Insert("insert into adoption (title,editor,type,location,text)values(#{title},#{editor},#{type},#{location},#{text})")
//	public Integer add(AdoptionNote newAdoption);
	
	@Select("select max(id) from adoption")
	public Integer getMaxId();
	
	@Insert("insert into adoption (id,title,editor,type,location,text)values(#{id},#{title},#{editor},#{type},#{location},#{text})")
	public Integer addAdoption(Integer id, String title, Integer editor, String type, String location, String text);
	
	@Delete("delete from adoption where id=#{arg1}")
	public Integer deleteAdoptionByID(Integer id);
	
	@Select("select * from adoption where id = #{id}")
	public AdoptionNote getAdoptionByID(Integer id);
	
	@Select("select * from adoption where state = 0 limit #{begin}, #{end}")
	public List<AdoptionNote> getAdoptionList(Integer begin, Integer end);
	
	@Select("select title from adoption where state = 0 limit #{begin}, #{end}")
	public List<String> getTitleList(Integer begin, Integer end);
	
	@SelectProvider(type=SqlProvider.class,method="getAdoption")
	public List<AdoptionNote> getAdoption(String title,String location,String type);
	
}

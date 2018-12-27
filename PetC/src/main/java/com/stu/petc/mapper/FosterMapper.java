package com.stu.petc.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.util.SqlProvider;;

@Mapper
public interface FosterMapper {
//	@Insert("insert into foster (title,editor,type,location,text)values(#{title},#{editor},#{type},#{location},#{text})")
//	public Integer add(FosterNote newFoster);
	
	
	@Select("select count(id) from foster")
	public Integer getTotal();
	
	@Select("select max(id) from foster")
	public Integer getMaxId();
	
	@SelectProvider(type=SqlProvider.class,method="getFosterTotalNumber")
	public Integer getTotalNumber(String title,String location,String type);
	
	@Insert("insert into foster (id,title,editor,type,location,text,startdate)values(#{id},#{title},#{editor},#{type},#{location},#{text},#{startdate})")
	public Integer addFoster(Integer id, String title, Integer editor, String type, String location, String text, Date startdate);
	
	@Delete("delete from foster where id=#{id}")
	public Integer deleteFosterByID(Integer id);
	
	@Delete("delete from foster_apply where foster_id=#{id}")
	public Integer deleteFosterApplyByID(Integer id);

//	@Update("update user set username=#{username},password=#{password},user_tel=#{user_tel} where user_id=#{user_id}")
//	public Integer update(User user);
//	
	@Select("select * from foster where id = #{id}")
	public FosterNote getFosterByID(Integer id);
	
	@Select("select * from foster where state = 0 limit #{begin}, #{end}")
	public List<FosterNote> getFosterList(Integer begin, Integer end);
	
	@Select("select * from foster where editor = #{user_id}")
	public List<FosterNote> getFosterListByUser(Integer user_id);
	
	@Select("select title from foster where state = 0 limit #{begin}, #{end}")
	public List<String> getTitleList(Integer begin, Integer end);
	

	
	@SelectProvider(type=SqlProvider.class,method="getFoster")
	public List<FosterNote> getFoster(String title,String location,String type,Integer page);
	
	@Update("update foster set unread = unread + 1 where id =#{id}")
	public Integer updateFosterageUnread(Integer id);
	
	@Select("select count(*) from foster_apply where foster_id = #{id} and applier = #{user_id}")
	public Integer checkOffer(Integer id,Integer user_id);
	
	@Insert("insert into foster_apply (foster_id,applier)values(#{id},#{user_id})")
	public Integer addOffer(Integer id,Integer user_id);
	
	
//	@Select("select * from foster where id = #{id}")
//	public FosterNote getUserByID(Integer id);
//	
//	@Select("select * from user where username = #{username}")
//	public User getUserByName(String username);
//	
//	@Select("select * from user where username = #{username} and password = #{password}")
//	public User checkUser(User user);
//	
//	@Select("select * from user")
//	public List<User> getAllUsers();
//	
//	@Select("select password from user where username=#{username}")
//	public String getPassword(String username);
//
//	@Select("select user_role from user where username=#{username}")
//	public String getRole(String username);
}

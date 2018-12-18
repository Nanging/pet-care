package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.stu.petc.beans.User;

@Mapper
public interface UserMapper {
	
	@Insert("insert into user (username,password,user_tel)values(#{username},#{password},#{user_tel})")
	public Integer add(User user);
	
	@Delete("delete from user where user_id=#{arg1}")
	public Integer deleteUserByID(Integer user_id);
	
	@Update("update user set username=#{username},password=#{password},user_tel=#{user_tel} where user_id=#{user_id}")
	public Integer update(User user);
	
	@Update("update user set user_tel = #{user_tel} where user_id=#{userid}")
	public Integer changeUserTle(Integer userid,String user_tel);
	
	@Select("select * from user where user_id = #{user_id}")
	public User getUserByID(Integer user_id);
	
	@Select("select * from user where username = #{username}")
	public User getUserByName(String username);
	
	@Select("select * from user where username = #{username} and password = #{password}")
	public User checkUser(User user);
	
	@Select("select * from user")
	public List<User> getAllUsers();
	
	@Select("select password from user where username=#{username}")
	public String getPassword(String username);

	@Select("select user_role from user where username=#{username}")
	public String getRole(String username);
}

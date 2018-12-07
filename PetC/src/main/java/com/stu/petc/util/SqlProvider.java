package com.stu.petc.util;

import java.util.List;

public class SqlProvider {
	public String getFoster(String title,String location,String type){
		StringBuffer sql = new StringBuffer("select * from foster where state = 0");
		if (!"All".equals(location)) {
			sql.append(" and location=#{location}");
		}
		if (!"All".equals(type)) {
			sql.append(" and type=#{type}");
		}
		System.out.println("[title:"+title+"]");
		if (title!=null&&!"".equals(title)) {
			sql.append(" and title LIKE '%"+title+"%'");
		}
		return sql.toString();
	}
	public String getAdoption(String title,String location,String type){
		StringBuffer sql = new StringBuffer("select * from adoption where state = 0");
		if (!"All".equals(location)) {
			sql.append(" and location=#{location}");
		}
		if (!"All".equals(type)) {
			sql.append(" and type=#{type}");
		}
		System.out.println("[title:"+title+"]");
		if (title!=null&&!"".equals(title)) {
			sql.append(" and title LIKE '%"+title+"%'");
		}
		return sql.toString();
	}
}

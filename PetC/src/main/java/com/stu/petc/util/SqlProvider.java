package com.stu.petc.util;

import java.util.List;

public class SqlProvider {
	
	private int noteNumber = 20;
	public String getFosterTotalNumber(String title,String location,String type){
		StringBuffer sql = new StringBuffer("select count(*) from foster where state = 0");
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
	
	public String getAdoptionTotalNumber(String title,String location,String type){
		StringBuffer sql = new StringBuffer("select count(*) from foster where state = 0");
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
	
	public String getShareTotalNumber(String title,String type){
		StringBuffer sql = new StringBuffer("select count(*) from foster where state = 0");
		if (!"All".equals(type)) {
			sql.append(" and type=#{type}");
		}
		System.out.println("[title:"+title+"]");
		if (title!=null&&!"".equals(title)) {
			sql.append(" and title LIKE '%"+title+"%'");
		}
		return sql.toString();
	}
	
	public String getFoster(String title,String location,String type,Integer page){
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
		if (page<1) {
			page = 1;
		}
		int offset = 20*(page-1);
		sql.append(" limit "+offset+" , "+noteNumber);
		return sql.toString();
	}
	public String getAdoption(String title,String location,String type,Integer page){
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
		if (page<1) {
			page = 1;
		}
		int offset = 20*(page-1);
		sql.append(" limit "+offset+" , "+noteNumber);
		return sql.toString();
	}
	public String getShare(String title,String type,Integer page){
		StringBuffer sql = new StringBuffer("select * from share where 0 = 0");
		if (!"All".equals(type)) {
			sql.append(" and type=#{type}");
		}
		System.out.println("[title:"+title+"]");
		if (title!=null&&!"".equals(title)) {
			sql.append(" and title LIKE '%"+title+"%'");
		}
		if (page<1) {
			page = 1;
		}
		int offset = 20*(page-1);
		sql.append(" limit "+offset+" , "+noteNumber);
		return sql.toString();
	}
}

package com.stu.petc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.stu.petc.beans.FosterNote;
import com.stu.petc.beans.ShareCommenter;
import com.stu.petc.beans.ShareNote;
import com.stu.petc.util.SqlProvider;

@Mapper
public interface ShareMapper {
//	@Insert("insert into share (title,editor,type,content)values(#{title},#{editor},#{type},#{content})")
//	public Integer addShare(ShareNote newShare);
	
	@Select("select max(id) from share")
	public Integer getMaxId();
	
	@Insert("insert into share (id,title,editor,type,content)values(#{id},#{title},#{editor},#{type},#{content})")
	public Integer addShare(Integer id,String title, Integer editor, String type, String content);

	@Delete("delete from share where id=#{arg1}")
	public Integer deleteShareByID(Integer id);
	
	@Select("select * from share where id = #{id}")
	public ShareNote getShareByID(Integer id);
	
	@Select("select * from share limit #{begin}, #{end}")
	public List<ShareNote> getShareList(Integer begin, Integer end);
	
	@Select("select title from share limit #{begin}, #{end}")
	public List<String> getTitleList(Integer begin, Integer end);
	
	@Update("update share set share.like = share.like + 1 where id = #{id};")
	public Integer updateLike(Integer id);
	
	@Insert("insert into share_like (share_id,user_id)values(#{share_id},#{user_id})")
	public Integer addLike(Integer share_id,Integer user_id);
	
	@Update("update share set share.comment = share.comment + 1 where id = #{id};")
	public Integer updateComment(Integer id);
	
	@Insert("insert into share_comment (share_id,commenter,comment)values(#{share_id},#{commenter},#{comment})")
	public Integer addComment(Integer share_id,Integer commenter,String comment);
	
	
	@SelectProvider(type=SqlProvider.class,method="getShare")
	public List<ShareNote> getShare(String title,String type);
	
	@Select("select count(*) from share_like where share_id = #{id}")
	public Integer getLikesNumber(Integer id);
	
	@Select("select count(*) from share_comment where share_id = #{id}")
	public Integer getCommentsNumber(Integer id);
	
	@Select("select * from share where editor= #{useid}")
	public List<ShareNote> getAllShareByUser(Integer useid);

	@Select("select share_comment.commenter,user.username,share_comment.comment,share_comment.comment_date "
			+ "from share_comment, user "
			+ "where share_comment.share_id = #{shareid} and user.user_id = share_comment.commenter ")
	public List<ShareCommenter> getShareCommenters(Integer shareid);
	
	@Select("select * from share where id in ("
			+ "select share_id from share_comment where commenter = #{useid} )")
	public List<ShareNote> getAllCommentedShareByUser(Integer useid);
	
}

package me.paul.yiblog.dao;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.Comment;

public interface ICommentDao {

	void save(Comment comment);

	void update(Comment comment);

	Comment get(Serializable id);
	
	List<Comment> getByPassage(Serializable passageid);
	
	int getNewCommentCount(long toUserId);
	
	int getAllCommentCount(long toUserId);
	
	List<Comment> getNewComment(long toUserId);

	List<Comment> getComment(long toUserId, int page, int commentPerPage);
	
}
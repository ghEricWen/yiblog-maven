package me.paul.yiblog.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.ICommentDao;
import me.paul.yiblog.entity.Comment;
import me.paul.yiblog.util.HibernateUtil;

public class CommentDaoImpl implements ICommentDao{

	private HibernateUtil util ;
	
	public void setUtil(HibernateUtil util) {
		this.util = util;
	}
	
	public void save(Comment comment) {
		util.save(comment);
	}

	public void update(Comment comment) {
		util.update(comment);
	}

	public Comment get(Serializable id) {
		return (Comment) util.get(Comment.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getByPassage(Serializable passageid) {
		String hql = "from Comment c where c.passage.id = ?";		
		return (List<Comment>) util.query(hql, passageid);
	}

	public int getNewCommentCount(long toUserId) {
		int count = 0;
		String hql = "select count(c) from Comment c where c.toUser.id = ? and c.newComment = 1";
		count = util.getCount(hql,toUserId);
		return count;
	}

	public List<Comment> getNewComment(long toUserId) {
		String hql = "from Comment c where c.toUser.id = ? and c.newComment = 1";
		@SuppressWarnings("unchecked")
		List<Comment> list = (List<Comment>) util.query(hql, toUserId);
		return list;
	}

	public List<Comment> getComment(long toUserId, int page, int commentPerPage) {
		String hql = "from Comment c where c.toUser.id = ? ";
		@SuppressWarnings("unchecked")
		List<Comment> list = (List<Comment>) util.limitQuery(hql, (page - 1) * commentPerPage, commentPerPage, toUserId);
		return list;
	}

	public int getAllCommentCount(long toUserId) {
		String hql = "select count(c) from Comment as c where c.toUser.id = ?";
		int count = 0;
		count = util.getCount(hql, toUserId);
		return count;
	}
	
	

}

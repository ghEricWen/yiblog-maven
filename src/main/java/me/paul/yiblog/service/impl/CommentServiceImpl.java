package me.paul.yiblog.service.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.ICommentDao;
import me.paul.yiblog.entity.Comment;
import me.paul.yiblog.service.ICommentService;

public class CommentServiceImpl implements ICommentService {

	private ICommentDao commentDao;

	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public void save(Comment comment) {
		commentDao.save(comment);
	}

	@Override
	public void update(Comment comment) {
		commentDao.update(comment);
	}

	@Override
	public Comment get(Serializable id) {
		return commentDao.get(id);
	}

	@Override
	public List<Comment> getByPassage(Serializable passageid) {
		return commentDao.getByPassage(passageid);
	}

	@Override
	public int getNewCommentCount(long toUserId) {
		return commentDao.getNewCommentCount(toUserId);
	}

	@Override
	public List<Comment> getNewComment(long toUserId) {
		return commentDao.getNewComment(toUserId);
	}

	@Override
	public List<Comment> getComment(long toUserId, int page, int commentPerPage) {
		return commentDao.getComment(toUserId, page, commentPerPage);
	}

	@Override
	public int getAllCommentCount(long toUserId) {
		return commentDao.getAllCommentCount(toUserId);
	}

}

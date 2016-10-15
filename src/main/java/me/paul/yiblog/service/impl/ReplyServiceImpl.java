package me.paul.yiblog.service.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.IReplyDao;
import me.paul.yiblog.entity.Reply;
import me.paul.yiblog.service.IReplyService;

public class ReplyServiceImpl implements IReplyService {

	private IReplyDao replyDao;

	public void setReplyDao(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public void save(Reply reply) {
		replyDao.save(reply);
	}

	@Override
	public void update(Reply reply) {
		replyDao.update(reply);
	}

	@Override
	public Reply get(Serializable id) {
		return replyDao.get(id);
	}

	@Override
	public List<Reply> getByComment(Serializable commentId) {
		return replyDao.getByComment(commentId);
	}

	@Override
	public int getNewReplyCount(long toUserId) {
		return replyDao.getNewReplyCount(toUserId);
	}

	@Override
	public List<Reply> getNewReply(long toUserId) {
		return replyDao.getNewReply(toUserId);
	}

	@Override
	public List<Reply> getReply(long toUserId, int page, int replyPerPage) {
		return replyDao.getReply(toUserId, page, replyPerPage);
	}

	@Override
	public int getAllReplyCount(long toUserId) {
		return replyDao.getAllReplyCount(toUserId);
	}

}

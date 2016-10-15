package me.paul.yiblog.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.IReplyDao;
import me.paul.yiblog.entity.Reply;
import me.paul.yiblog.util.HibernateUtil;

public class ReplyDaoImpl implements IReplyDao {

	private HibernateUtil util;

	public void setUtil(HibernateUtil util) {
		this.util = util;
	}

	public void save(Reply reply) {
		util.save(reply);
	}

	public void update(Reply reply) {
		util.update(reply);
	}

	public Reply get(Serializable id) {
		return (Reply) util.get(Reply.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Reply> getByComment(Serializable commentId) {
		String hql = "from Reply r where r.comment.id = ?";
		return (List<Reply>) util.query(hql, commentId);
	}

	public int getNewReplyCount(long toUserId) {
		int count = 0;
		String hql = "select count(r) from Reply as r where r.toUser.id = ? and r.newreply = 1";
		count = util.getCount(hql, toUserId);
		return count;
	}

	public List<Reply> getNewReply(long toUserId) {
		String hql = "from Reply r where r.toUser.id = ? and r.newreply = 1";
		@SuppressWarnings("unchecked")
		List<Reply> list = (List<Reply>) util.query(hql, toUserId);
		return list;
	}

	public List<Reply> getReply(long toUserId, int page, int replyPerPage) {
		String hql = "from Reply r where r.toUser.id = ?";
		@SuppressWarnings("unchecked")
		List<Reply> list = (List<Reply>) util.limitQuery(hql, (page - 1) * replyPerPage, replyPerPage, toUserId);
		return list;
	}

	public int getAllReplyCount(long toUserId) {
		String hql = "select count(r) from Reply as r where r.toUser.id = ?";
		int count = 0;
		count = util.getCount(hql,toUserId);
		return count;
	}

}

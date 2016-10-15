package me.paul.yiblog.dao;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.Reply;

public interface IReplyDao {

	void save(Reply reply);

	void update(Reply reply);

	Reply get(Serializable id);

	List<Reply> getByComment(Serializable commentId);

	int getNewReplyCount(long toUserId);
	
	int getAllReplyCount(long toUserId);
	
	List<Reply> getNewReply(long toUserId);
	
	List<Reply> getReply(long toUserId,int page,int replyPerPage);

}

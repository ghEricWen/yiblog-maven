package me.paul.yiblog.entity;

import java.util.Date;

public class Comment {
	private long id;

	private Passage passage;

	private User fromUser;

	private User toUser;

	private String content;

	private Date commenttime;

	private int replyCount;

	private int newComment;

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Passage getPassage() {
		return passage;
	}

	public void setPassage(Passage passage) {
		this.passage = passage;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}

	public int getNewComment() {
		return newComment;
	}

	public void setNewComment(int newComment) {
		this.newComment = newComment;
	}

	@Override
	public String toString() {
		return content;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Comment) {
			Comment c = (Comment) obj;
			if (c.getId() == id) {
				return true;
			}
		}
		return false;

	}
	
	@Override
	public int hashCode() {
		return (int)id;
	}

}

package me.paul.yiblog.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import me.paul.yiblog.entity.Comment;
import me.paul.yiblog.entity.Reply;
import me.paul.yiblog.entity.User;
import me.paul.yiblog.service.ICommentService;
import me.paul.yiblog.service.IReplyService;
import me.paul.yiblog.util.CommonUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReplyAction extends ActionSupport {

	private static final long serialVersionUID = 7911788803952912838L;

	private IReplyService replyService;

	private ICommentService commentService;

	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	private Reply reply;

	private Comment comment;

	private User fromUser;

	private User toUser;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	private long passageid;

	public long getPassageid() {
		return passageid;
	}

	private int page;

	private int replyPerPage;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getReplyPerPage() {
		return replyPerPage;
	}

	public void setReplyPerPage(int replyPerPage) {
		this.replyPerPage = replyPerPage;
	}

	public void setPassageid(long passageid) {
		this.passageid = passageid;
	}

	//提交回复
	public synchronized String save() {
		reply.setFromUser(fromUser);
		reply.setToUser(toUser);
		reply.setNewreply(1);
		reply.setReplytime(new Date());
		synchronized (ReplyAction.class) {
			comment = commentService.get(comment.getId());
			comment.setReplyCount(comment.getReplyCount() + 1);
			commentService.update(comment);
			reply.setComment(comment);
			replyService.save(reply);
		}
		return "toPassage";
	}
	
	//提交但不转发
	public synchronized String onlySave() throws IOException{
		reply.setFromUser(fromUser);
		reply.setToUser(toUser);
		reply.setNewreply(1);
		reply.setReplytime(new Date());
		synchronized (ReplyAction.class) {
			comment = commentService.get(comment.getId());
			comment.setReplyCount(comment.getReplyCount() + 1);
			commentService.update(comment);
			reply.setComment(comment);
			replyService.save(reply);
		}
		return null;
	}

	//获取某用户新回复的数量
	public String getCount() throws IOException {
		long id = toUser.getId();
		int count = replyService.getNewReplyCount(id);
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		writer.write(String.valueOf(count));
		return null;
	}
	
	//获取新回复
	public String getNewReply() {
		long id = toUser.getId();
		List<Reply> list = replyService.getNewReply(id);
		ActionContext.getContext().getContextMap().put("newReplys", list);
		return "newReply";
	}

	//分页获取回复
	public String getReplys() {
		long id = toUser.getId();
		List<Reply> list = replyService.getReply(id, page, replyPerPage);
		int count = replyService.getAllReplyCount(id);
		int pageCount = (int) Math.ceil(count * 1.0 / replyPerPage);
		Map<String,Integer> map = CommonUtil.getPageMap(pageCount, page);
		Map<String,Object> request = ActionContext.getContext().getContextMap(); 
		request.put("listReply", list);
		request.put("mapPage", map);
		request.put("currentPage", page);
		request.put("nextPage", page +1 );
		request.put("lastPage", page -1);
		request.put("pageCount", pageCount);
		return "replyList";
	}
	
	//将回复标记为已读
	public String markRead(){
		long id = reply.getId();
		reply = replyService.get(id);
		reply.setNewreply(0);
		replyService.update(reply);
		return "markRead";
	}

}

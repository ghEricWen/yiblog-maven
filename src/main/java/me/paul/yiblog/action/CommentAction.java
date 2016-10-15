package me.paul.yiblog.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import me.paul.yiblog.entity.Comment;
import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.entity.User;
import me.paul.yiblog.service.ICommentService;
import me.paul.yiblog.util.CommonUtil;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport {

	private static final long serialVersionUID = 5816530900340080938L;

	private Comment comment;

	private Passage passage;

	private User fromUser;

	private User toUser;

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCommentPerPage() {
		return commentPerPage;
	}

	public void setCommentPerPage(int commentPerPage) {
		this.commentPerPage = commentPerPage;
	}

	private int commentPerPage;

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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Passage getPassage() {
		return passage;
	}

	public void setPassage(Passage passage) {
		this.passage = passage;
	}

	private ICommentService commentService;

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	//添加评论
	public synchronized String save() {
		comment.setPassage(passage);
		comment.setCommenttime(new Date());
		comment.setFromUser(fromUser);
		comment.setToUser(toUser);
		comment.setNewComment(1);
		commentService.save(comment);
		return "toPassage";
	}

	//得到某管理员所收到的评论数
	public String getCount() throws IOException {
		int count = commentService.getNewCommentCount(toUser.getId());
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		writer.write(String.valueOf(count));
		return null;
	}

	//获取newComment
	public String getNewComment() {
		long id = toUser.getId();
		List<Comment> newComments = commentService.getNewComment(id);
		ActionContext.getContext().getContextMap()
				.put("newComments", newComments);
		return "newComment";
	}

	//分页获取comments
	public String getComments() {
		long id = toUser.getId();
		List<Comment> list = commentService.getComment(id, page, commentPerPage);
		int count = commentService.getAllCommentCount(id);
		int pageCount = (int) Math.ceil(count * 1.0 / commentPerPage);
		Map<String,Integer> map = CommonUtil.getPageMap(pageCount,page);
		Map<String,Object> request = ActionContext.getContext().getContextMap();
		request.put("listComment",list);
		request.put("mapPage",map);
		request.put("currentPage", page);
		request.put("nextPage", page +1 );
		request.put("lastPage", page -1);
		request.put("pageCount", pageCount);
		return "commentList";
	}
	
	//将评论标记为已读
	public synchronized String markRead(){
		long id = comment.getId();
		comment = commentService.get(id);
		comment.setNewComment(0);
		commentService.update(comment);
		return "markRead";
	}

}

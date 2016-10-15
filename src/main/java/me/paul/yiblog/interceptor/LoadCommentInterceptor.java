package me.paul.yiblog.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import me.paul.yiblog.entity.Comment;
import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.entity.Reply;
import me.paul.yiblog.service.ICommentService;
import me.paul.yiblog.service.IReplyService;

public class LoadCommentInterceptor implements Interceptor{

	private static final long serialVersionUID = -5379743802608929975L;

	public void destroy() {
		
	}

	public void init() {
		
	}
	
	private ICommentService commentService;
	
	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}
	
	private IReplyService replyService ;
	
	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Passage passage = (Passage) request.getAttribute("passage");
		if(passage == null) return invocation.invoke();
		long passageid = passage.getId();
		Map<Comment,List<Reply>> map = new HashMap<Comment,List<Reply>>(); 
		List<Comment> listComment = commentService.getByPassage(passageid);
		for(Comment c: listComment){
			List<Reply> listReply = replyService.getByComment(c.getId());
			map.put(c, listReply);
		}
		request.setAttribute("mapComment", map);
		request.setAttribute("listCommentSize", listComment.size());
		return invocation.invoke();
	}

}

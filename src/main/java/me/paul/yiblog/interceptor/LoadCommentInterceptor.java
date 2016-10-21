package me.paul.yiblog.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.paul.yiblog.dao.ICommentDao;
import me.paul.yiblog.dao.IReplyDao;
import me.paul.yiblog.entity.Comment;
import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.entity.Reply;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoadCommentInterceptor implements Interceptor{

	private static final long serialVersionUID = -5379743802608929975L;

	public void destroy() {
		
	}

	public void init() {
		
	}
	
	private ICommentDao commentDao;
	
	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	private IReplyDao replyDao ;
	
	public void setReplyDao(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Passage passage = (Passage) request.getAttribute("passage");
		if(passage == null) return invocation.invoke();
		long passageid = passage.getId();
		Map<Comment,List<Reply>> map = new HashMap<Comment,List<Reply>>(); 
		List<Comment> listComment = commentDao.getByPassage(passageid);
		for(Comment c: listComment){
			List<Reply> listReply = replyDao.getByComment(c.getId());
			map.put(c, listReply);
		}
		request.setAttribute("mapComment", map);
		request.setAttribute("listCommentSize", listComment.size());
		return invocation.invoke();
	}

}

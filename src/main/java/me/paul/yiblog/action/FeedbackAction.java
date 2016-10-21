package me.paul.yiblog.action;

import java.util.Date;
import java.util.List;

import me.paul.yiblog.dao.IFeedbackDao;
import me.paul.yiblog.entity.Feedback;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FeedbackAction extends ActionSupport{

	private static final long serialVersionUID = 2239950023141811980L;
	
	private IFeedbackDao feedbackDao ;
	
	public void setFeedbackDao(IFeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}
	
	private Feedback feedback;
	
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	public Feedback getFeedback() {
		return feedback;
	}
	
	public String save(){
		feedback.setFeedbackDate(new Date());
		feedbackDao.save(feedback);
		ActionContext.getContext().getContextMap().put("message", "谢谢你的宝贵意见");
		return "feedback";
	}
	
	public String list(){
		List<Feedback> list = feedbackDao.getAll();
		ActionContext.getContext().getContextMap().put("listFeedback", list);
		return "feedbackList";
	}

}

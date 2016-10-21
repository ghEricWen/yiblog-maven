package me.paul.yiblog.dao.impl;

import java.util.List;

import me.paul.yiblog.dao.IFeedbackDao;
import me.paul.yiblog.entity.Feedback;
import me.paul.yiblog.util.HibernateUtil;

public class FeedbackDaoImpl implements IFeedbackDao {

	private HibernateUtil util;

	public void setUtil(HibernateUtil util) {
		this.util = util;
	}

	@Override
	public void save(Feedback fb) {
		util.save(fb);
	}

	@Override
	public List<Feedback> getAll() {
		String hql = "from Feedback as f order by f.feedbackDate desc";
		@SuppressWarnings("unchecked")
		List<Feedback> list = (List<Feedback>) util.query(hql);
		return list;
	}

}

package me.paul.yiblog.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.IAnnouncementDao;
import me.paul.yiblog.entity.Announcement;
import me.paul.yiblog.util.HibernateUtil;

public class AnnouncementDaoImpl implements IAnnouncementDao{

	private HibernateUtil util ;
	
	public void setUtil(HibernateUtil util) {
		this.util = util;
	}
	
	public void save(Announcement an) {
		util.save(an);
	}

	public Announcement getLatest() {
		String hql = "from Announcement as a order by a.time desc";
		@SuppressWarnings("unchecked")
		List<Announcement> list = (List<Announcement>) util.limitQuery(hql, 0, 1);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public Announcement get(Serializable id) {
		return (Announcement) util.get(Announcement.class, id);
	}

	public void update(Announcement an) {
		util.update(an);
		
	}

}

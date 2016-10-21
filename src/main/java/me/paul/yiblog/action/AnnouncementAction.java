package me.paul.yiblog.action;

import java.util.Date;

import me.paul.yiblog.dao.IAnnouncementDao;
import me.paul.yiblog.entity.Announcement;

import com.opensymphony.xwork2.ActionSupport;

public class AnnouncementAction  extends ActionSupport{

	private static final long serialVersionUID = -7516954256960401148L;
	
	private IAnnouncementDao announcementDao ;
	
	public void setAnnouncementDao(IAnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}
	
	private Announcement announcement;
	
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	
	public Announcement getAnnouncement() {
		return announcement;
	}
	
	public String save(){
		announcement.setTime(new Date());
		announcementDao.save(announcement);
		return "index";
	}
	
}

package me.paul.yiblog.action;

import java.util.Date;

import me.paul.yiblog.entity.Announcement;
import me.paul.yiblog.service.IAnnouncementService;

import com.opensymphony.xwork2.ActionSupport;

public class AnnouncementAction  extends ActionSupport{

	private static final long serialVersionUID = -7516954256960401148L;
	
	private IAnnouncementService announcementService ;
	
	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
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
		announcementService.save(announcement);
		return "index";
	}
	
}

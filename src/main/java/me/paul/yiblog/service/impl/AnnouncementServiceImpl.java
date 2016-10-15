package me.paul.yiblog.service.impl;

import java.io.Serializable;

import me.paul.yiblog.dao.IAnnouncementDao;
import me.paul.yiblog.entity.Announcement;
import me.paul.yiblog.service.IAnnouncementService;

public class AnnouncementServiceImpl implements IAnnouncementService{

	private IAnnouncementDao announcementDao;
	
	public void setAnnouncementDao(IAnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}
	
	@Override
	public void save(Announcement an) {
		announcementDao.save(an);
	}

	@Override
	public Announcement getLatest() {
		return announcementDao.getLatest();
	}

	@Override
	public Announcement get(Serializable id) {
		return announcementDao.get(id);
	}

	@Override
	public void update(Announcement an) {
		announcementDao.update(an);
	}
	

}

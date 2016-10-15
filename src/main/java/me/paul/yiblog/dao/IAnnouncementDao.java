package me.paul.yiblog.dao;

import java.io.Serializable;

import me.paul.yiblog.entity.Announcement;

public interface IAnnouncementDao {
	
	void save(Announcement an);
	
	Announcement getLatest();
	
	Announcement get(Serializable id);
	
	void update(Announcement an);

}

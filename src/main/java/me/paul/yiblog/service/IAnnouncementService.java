package me.paul.yiblog.service;

import java.io.Serializable;

import me.paul.yiblog.entity.Announcement;

public interface IAnnouncementService {

	void save(Announcement an);

	Announcement getLatest();

	Announcement get(Serializable id);

	void update(Announcement an);

}

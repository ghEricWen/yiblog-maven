package me.paul.yiblog.service;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.User;

public interface IUserService {
	void save(User user);

	void update(User user);

	User get(Serializable id);
	
	User getByName(String name);
	
	User getByEmail(String email);
	
	List<User> getUsers(int page,int userPerPage);
	
	int getUserCount();
}

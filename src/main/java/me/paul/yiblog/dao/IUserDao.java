package me.paul.yiblog.dao;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.User;

public interface IUserDao {

	void save(User user);

	void update(User user);

	User get(Serializable id);
	
	User getByName(String name);
	
	User getByEmail(String email);
	
	List<User> getUsers(int page,int userPerPage);

	int getUserCount();
}

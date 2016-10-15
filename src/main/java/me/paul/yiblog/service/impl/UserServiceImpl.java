package me.paul.yiblog.service.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.IUserDao;
import me.paul.yiblog.entity.User;
import me.paul.yiblog.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User get(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public User getByName(String name) {
		return userDao.getByName(name);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public List<User> getUsers(int page, int userPerPage) {
		return userDao.getUsers(page, userPerPage);
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}

}

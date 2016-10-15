package me.paul.yiblog.service.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.ICategoryDao;
import me.paul.yiblog.entity.Category;
import me.paul.yiblog.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	private ICategoryDao cateDao;

	public void setCateDao(ICategoryDao cateDao) {
		this.cateDao = cateDao;
	}

	@Override
	public void save(Category c) {
		cateDao.save(c);
	}

	@Override
	public void update(Category c) {
		cateDao.update(c);
	}

	@Override
	public Category get(Serializable id) {
		return cateDao.get(id);
	}

	@Override
	public List<Category> getAll() {
		return cateDao.getAll();
	}

	@Override
	public int getTotalPassageCount() {
		return cateDao.getTotalPassageCount();
	}

}

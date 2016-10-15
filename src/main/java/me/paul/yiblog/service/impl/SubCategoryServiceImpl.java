package me.paul.yiblog.service.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.ISubCategoryDao;
import me.paul.yiblog.entity.SubCategory;
import me.paul.yiblog.service.ISubCategoryService;

public class SubCategoryServiceImpl implements ISubCategoryService {
	
	private ISubCategoryDao subCategoryDao ;
	
	public void setSubCategoryDao(ISubCategoryDao subCategoryDao) {
		this.subCategoryDao = subCategoryDao;
	}

	@Override
	public void save(SubCategory subCategory) {
		subCategoryDao.save(subCategory);
	}

	@Override
	public void update(SubCategory subCategory) {
		subCategoryDao.update(subCategory);
	}

	@Override
	public SubCategory get(Serializable id) {
		return subCategoryDao.get(id);
	}

	@Override
	public List<SubCategory> getByCategory(Serializable categoryId) {
		return subCategoryDao.getByCategory(categoryId);
	}

}

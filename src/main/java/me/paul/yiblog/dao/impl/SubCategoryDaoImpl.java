package me.paul.yiblog.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.ISubCategoryDao;
import me.paul.yiblog.entity.SubCategory;
import me.paul.yiblog.util.HibernateUtil;

public class SubCategoryDaoImpl implements ISubCategoryDao{
	
	private HibernateUtil util ;
	
	public void setUtil(HibernateUtil util) {
		this.util = util;
	}

	public void save(SubCategory subCategory) {
		util.save(subCategory);
	}

	public void update(SubCategory subCategory) {
		util.update(subCategory);
	}

	public SubCategory get(Serializable id) {
		return (SubCategory) util.get(SubCategory.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<SubCategory> getByCategory(Serializable categoryId) {
		String hql = "from SubCategory sc where sc.category.id = ?";
		return (List<SubCategory>) util.query(hql, categoryId);
	}

}

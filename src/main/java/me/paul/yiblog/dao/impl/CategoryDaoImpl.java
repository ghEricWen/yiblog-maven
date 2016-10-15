package me.paul.yiblog.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.dao.ICategoryDao;
import me.paul.yiblog.entity.Category;
import me.paul.yiblog.util.HibernateUtil;

public class CategoryDaoImpl implements ICategoryDao{

	private HibernateUtil util ;
	
	public void setUtil(HibernateUtil util) {
		this.util = util;
	}	

	public void save(Category c) {
		util.save(c);
	}

	public void update(Category c) {
		util.update(c);
	}

	public Category get(Serializable id) {
		return (Category) util.get(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAll() {		
		return (List<Category>) util.query("from Category");
	}

	public int getTotalPassageCount() {		
		List<Category> list = getAll();
		int count = 0;
		for(Category category : list ){
			count += category.getPassageCount();
		}
		return count;
	}
	

}

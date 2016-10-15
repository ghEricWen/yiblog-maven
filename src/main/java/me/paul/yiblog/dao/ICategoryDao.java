package me.paul.yiblog.dao;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.Category;

public interface ICategoryDao {

	void save(Category c);

	void update(Category c);

	Category get(Serializable id);
	
	List<Category> getAll();
	
	int getTotalPassageCount();

}

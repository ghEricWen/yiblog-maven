package me.paul.yiblog.service;

import java.io.Serializable;
import java.util.List;

import me.paul.yiblog.entity.SubCategory;

public interface ISubCategoryService {

	void save(SubCategory subCategory);

	void update(SubCategory subCategory);

	SubCategory get(Serializable id);

	List<SubCategory> getByCategory(Serializable categoryId);
}

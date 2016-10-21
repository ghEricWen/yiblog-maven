package me.paul.yiblog.action;

import me.paul.yiblog.dao.ICategoryDao;
import me.paul.yiblog.entity.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1534344860213406467L;
	
	private ICategoryDao categoryDao;

	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String save() {
		category.setPassageCount(0);
		categoryDao.save(category);
		return "index";
	}
	
	public String update(){
		categoryDao.update(category);
		return "index";
	}
	
	public String get(){
		Category cateGet = categoryDao.get(category.getId());
		ActionContext.getContext().getContextMap().put("cateGet", cateGet);
		return "showCate";
	}

}

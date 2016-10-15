package me.paul.yiblog.action;

import me.paul.yiblog.entity.Category;
import me.paul.yiblog.service.ICategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1534344860213406467L;
	
	private ICategoryService cateService;

	public void setCateService(ICategoryService cateService) {
		this.cateService = cateService;
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
		cateService.save(category);
		return "index";
	}
	
	public String update(){
		cateService.update(category);
		return "index";
	}
	
	public String get(){
		Category cateGet = cateService.get(category.getId());
		ActionContext.getContext().getContextMap().put("cateGet", cateGet);
		return "showCate";
	}

}

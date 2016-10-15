package me.paul.yiblog.action;

import java.io.IOException;
import java.util.List;

import me.paul.yiblog.entity.Category;
import me.paul.yiblog.entity.SubCategory;
import me.paul.yiblog.service.ICategoryService;
import me.paul.yiblog.service.ISubCategoryService;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.opensymphony.xwork2.ActionSupport;

public class SubCategoryAction extends ActionSupport {

	private static final long serialVersionUID = 4946555677307680653L;

	private ISubCategoryService subCategoryService;
	
	private ICategoryService categoryService;
	
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setSubCategoryService(ISubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	private Category category;

	private SubCategory subCategory;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	//添加subCategory
	public String save() {
		Category cate = categoryService.get(category.getId());
		subCategory.setCategory(cate);
		subCategoryService.save(subCategory);
		return "index";
	}
	
	//ajax用cagory id 获取subCategory xml
	public String getXmlByCategory() throws  IOException{
		List<SubCategory> list = subCategoryService.getByCategory(category.getId());
		DocumentFactory docfactory = new DocumentFactory();
		Document doc = docfactory.createDocument();
		Element root = docfactory.createElement("subcategorys");
		for (SubCategory sc : list) {
			Element sub = docfactory.createElement("subcategory");
			Attribute idAttr = docfactory.createAttribute(sub, "id",String.valueOf(sc.getId()));
			Attribute nameAttr = docfactory.createAttribute(sub, "name",sc.getName());
			Attribute categoryAttr = docfactory.createAttribute(sub, "category",String.valueOf(category.getId()));
			Attribute passagetCountAttr = docfactory.createAttribute(sub, "passageCount",String.valueOf(sc.getPassageCount()));
			sub.add(idAttr);
			sub.add(nameAttr);
			sub.add(categoryAttr);
			sub.add(passagetCountAttr);
			root.add(sub);
		}
		doc.add(root);
		XMLWriter writer = new XMLWriter(ServletActionContext.getResponse()
				.getOutputStream(), OutputFormat.createCompactFormat());
		writer.write(doc);
		writer.close();
		return null;
	}

}

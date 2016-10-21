package me.paul.yiblog.action;

import java.io.IOException;
import java.util.List;

import me.paul.yiblog.dao.ICategoryDao;
import me.paul.yiblog.dao.ISubCategoryDao;
import me.paul.yiblog.entity.Category;
import me.paul.yiblog.entity.SubCategory;

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

	private ISubCategoryDao subCategoryDao;
	
	private ICategoryDao categoryDao;
	
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setSubCategoryDao(ISubCategoryDao subCategoryDao) {
		this.subCategoryDao = subCategoryDao;
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
		Category cate = categoryDao.get(category.getId());
		subCategory.setCategory(cate);
		subCategoryDao.save(subCategory);
		return "index";
	}
	
	//ajax用cagory id 获取subCategory xml
	public String getXmlByCategory() throws  IOException{
		List<SubCategory> list = subCategoryDao.getByCategory(category.getId());
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

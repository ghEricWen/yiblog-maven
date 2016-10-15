package me.paul.yiblog.action;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import me.paul.yiblog.entity.Announcement;
import me.paul.yiblog.entity.Category;
import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.entity.SubCategory;
import me.paul.yiblog.entity.User;
import me.paul.yiblog.service.IAnnouncementService;
import me.paul.yiblog.service.ICategoryService;
import me.paul.yiblog.service.IPassageService;
import me.paul.yiblog.service.ISubCategoryService;
import me.paul.yiblog.util.CommonUtil;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PassageAction extends ActionSupport {

	private static final long serialVersionUID = 1306752896985884525L;

	private IPassageService passService;

	private ICategoryService cateService;

	private ISubCategoryService subCategoryService;

	private IAnnouncementService announcementService;

	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public void setSubCategoryService(ISubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	public void setPassService(IPassageService passService) {
		this.passService = passService;
	}

	public void setCateService(ICategoryService cateService) {
		this.cateService = cateService;
	}

	private Passage passage;

	private Category category;

	private SubCategory subCategory;

	private User author;

	private int page;

	private int passagePerPage;

	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPassagePerPage() {
		return passagePerPage;
	}

	public void setPassagePerPage(int passagePerPage) {
		this.passagePerPage = passagePerPage;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setPassage(Passage passage) {
		this.passage = passage;
	}

	public Passage getPassage() {
		return passage;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// 添加文章
	public String save() {
		passage.setCategory(category);
		passage.setReadtime(0);
		passage.setAuthor(author);
		passage.setWritetime(new Date());
		passage.setSubCategory(subCategory);
		synchronized (PassageAction.class) {
			category = cateService.get(category.getId());
			subCategory = subCategoryService.get(subCategory.getId());
			category.setPassageCount(category.getPassageCount() + 1);
			subCategory.setPassageCount(subCategory.getPassageCount() + 1);
			cateService.update(category);
			subCategoryService.update(subCategory);
			passService.save(passage);
			Announcement announcement = announcementService.get(3l);
			announcement.setTime(new Date());
			announcementService.update(announcement);
		}
		return "index";
	}

	// 获取文章
	public String get() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		passage = passService.get(passage.getId());
		@SuppressWarnings("unchecked")
		List<Long> hasReadPassages = (List<Long>) session.getAttribute("read");
		if (hasReadPassages == null) {
			return "input";
		}
		if (!hasReadPassages.contains(passage.getId())) {
			synchronized (PassageAction.class) {
				passage.setReadtime(passage.getReadtime() + 1);
				passService.update(passage);
			}
			hasReadPassages.add(passage.getId());
		}

		session.setAttribute("read", hasReadPassages);
		ActionContext.getContext().getContextMap().put("passage", passage);
		return "viewPassage";
	}

	// 获取文章以编辑
	public String editPassage() {
		long id = passage.getId();
		Passage passage = passService.get(id);
		ActionContext.getContext().getContextMap().put("passageGet", passage);
		System.out.println(passage.getContent());
		return "editPassage";
	}

	// 确定提交编辑
	public synchronized String submitUpdate() {
		Passage passageGet = passService.get(passage.getId());
		passageGet.setContent(passage.getContent());
		passageGet.setTitle(passage.getTitle());
		passageGet.setCategory(category);
		passageGet.setSubCategory(subCategory);
		passService.update(passageGet);
		return "index";
	}

	// 分页
	public String page() {
		int count = 0;
		List<Passage> list = Collections.emptyList();
		Map<String, Object> request = ActionContext.getContext()
				.getContextMap();
		if (category != null) {
			category = cateService.get(category.getId());
			count = category.getPassageCount();
			request.put("currentCate", category.getName());
			List<SubCategory> listSub = subCategoryService
					.getByCategory(category.getId());
			request.put("listSubCategory", listSub);
			if (order == null) {
				list = passService.categoryPage(page, passagePerPage,
						category.getId());
			} else if (order.equals("reverse")) {
				list = passService.categoryPage(page, count, category.getId(),
						true);
			}

		}
		if (subCategory != null) {
			subCategory = subCategoryService.get(subCategory.getId());
			count = subCategory.getPassageCount();
			request.put("currentCate", subCategory.getName());
			List<SubCategory> listSub = subCategoryService
					.getByCategory(subCategory.getCategory().getId());
			request.put("listSubCategory", listSub);
			if (order == null) {
				list = passService.subCategoryPage(page, passagePerPage,
						subCategory.getId());
			} else if (order.equals("reverse")) {
				list = passService.subCategoryPage(page, passagePerPage,
						subCategory.getId(), true);
			}
		}
		if (category == null && subCategory == null) {
			count = cateService.getTotalPassageCount();
			request.put("currentCate", "all");
			if (order == null) {
				list = passService.page(page, passagePerPage);
			} else if (order.equals("reverse")) {
				list = passService.page(page, passagePerPage, true);
			}
		}
		int pageCount = (int) Math.ceil(count * 1.0 / passagePerPage);
		Map<String, Integer> mapPage = CommonUtil.getPageMap(pageCount, page);
		request.put("listPassage", list);
		request.put("mapPage", mapPage);
		request.put("currentPage", page);
		request.put("nextPage", page + 1);
		request.put("lastPage", page - 1);
		request.put("pageCount", pageCount);
		return "passageList";
	}
}

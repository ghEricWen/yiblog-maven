package me.paul.yiblog.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import me.paul.yiblog.dao.ICategoryDao;
import me.paul.yiblog.entity.Category;

public class LoadCategoryFilter implements Filter {

	public void destroy() {

	}

	private ICategoryDao categoryDao;

	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		List<Category> cateList = categoryDao.getAll();
		request.setAttribute("listCategory", cateList);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
	}

}

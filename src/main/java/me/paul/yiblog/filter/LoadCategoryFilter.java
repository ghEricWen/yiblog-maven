package me.paul.yiblog.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import me.paul.yiblog.entity.Category;
import me.paul.yiblog.service.ICategoryService;

public class LoadCategoryFilter implements Filter {

	public void destroy() {

	}

	private ICategoryService categoryService;

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		List<Category> cateList = categoryService.getAll();
		request.setAttribute("listCategory", cateList);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
	}

}

package me.paul.yiblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class LoadCategoryFilterProxy implements Filter {

	public void destroy() {
		filter.destroy();
	}

	private Filter filter;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		filter.doFilter(request, response, chain);
	}

	public void init(FilterConfig config) throws ServletException {
		WebApplicationContext ac ;
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		filter = (Filter) ac.getBean("loadCategoryFilter");
		filter.init(config);		
	}

}

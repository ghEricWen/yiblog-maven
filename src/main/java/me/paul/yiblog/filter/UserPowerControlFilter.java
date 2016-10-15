package me.paul.yiblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.paul.yiblog.entity.User;

public class UserPowerControlFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		HttpServletResponse resp = (HttpServletResponse) response;
		User currentUser = (User) session.getAttribute("currentUser");
		
		if(currentUser == null ){
			resp.sendRedirect(contextPath + "/signin.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	private String contextPath;
	
	public void init(FilterConfig config) throws ServletException {
		contextPath = config.getServletContext().getContextPath();
		
	}

}

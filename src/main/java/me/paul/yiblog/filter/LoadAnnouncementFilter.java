package me.paul.yiblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import me.paul.yiblog.entity.Announcement;
import me.paul.yiblog.service.IAnnouncementService;

public class LoadAnnouncementFilter implements Filter{

	public void destroy() {
		
	}

	private IAnnouncementService announcementService ;
	
	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Announcement announcement = announcementService.getLatest();
		request.setAttribute("announcement", announcement);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

}

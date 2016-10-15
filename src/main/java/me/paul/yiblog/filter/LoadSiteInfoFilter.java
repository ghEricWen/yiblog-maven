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

public class LoadSiteInfoFilter implements Filter{

	public void destroy() {
		
	}
	
	private IAnnouncementService announcementService;
	
	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Announcement buildSite = announcementService.get(2l);
		Announcement lastUpdateTime = announcementService.get(3l);
		request.setAttribute("buildSite", buildSite);
		request.setAttribute("lastUpdate", lastUpdateTime);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

}

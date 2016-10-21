package me.paul.yiblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import me.paul.yiblog.dao.IAnnouncementDao;
import me.paul.yiblog.entity.Announcement;

public class LoadSiteInfoFilter implements Filter{

	public void destroy() {
		
	}
	
	private IAnnouncementDao announcementDao;
	
	public void setAnnouncementDao(IAnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Announcement buildSite = announcementDao.get(2l);
		Announcement lastUpdateTime = announcementDao.get(3l);
		request.setAttribute("buildSite", buildSite);
		request.setAttribute("lastUpdate", lastUpdateTime);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

}

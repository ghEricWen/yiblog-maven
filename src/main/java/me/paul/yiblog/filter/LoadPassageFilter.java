package me.paul.yiblog.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import me.paul.yiblog.entity.Passage;
import me.paul.yiblog.service.IPassageService;

public class LoadPassageFilter implements Filter {

	private int count;

	public void setCount(int count) {
		this.count = count;
	}

	private IPassageService passageService;

	public void setPassageService(IPassageService passageService) {
		this.passageService = passageService;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		List<Passage> listMost = passageService.mostRead(count);
		List<Passage> listLatest = passageService.latest(count);
		request.setAttribute("mostRead", listMost);
		request.setAttribute("latest", listLatest);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {

	}

}

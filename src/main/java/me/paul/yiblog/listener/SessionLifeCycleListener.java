package me.paul.yiblog.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionLifeCycleListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		List<Long> list = new ArrayList<Long>();
		event.getSession().setAttribute("read", list);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		event.getSession().removeAttribute("read");
	}

}

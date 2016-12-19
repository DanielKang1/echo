package com.echo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CheckOrderDataTaskListener implements ServletContextListener {

	@Autowired
	private TimerManager timermanager;
	
	public void contextInitialized(ServletContextEvent event) {
		//Listener在初始化时，交给Spring来装配的TimerManager为空，故要执行下面一句来装配timermanager。
		WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext()) .getAutowireCapableBeanFactory().autowireBean(this);  
		timermanager.start();
	}
	
	public void contextDestroyed(ServletContextEvent event) {
	}

}
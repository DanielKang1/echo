package com.echo.service;

import org.apache.log4j.Logger;

public class testService {
	
	public static final Logger logger = Logger.getLogger(testService.class);
	
	public void add(){
		
		
		logger.debug("添加了用户信息--debug");
		logger.info("添加了用户信息--info");
		logger.warn("添加了用户信息--warn");
		logger.error("添加了用户信息--error");
		logger.fatal("添加了用户信息--fatal");
	}
	
 

}

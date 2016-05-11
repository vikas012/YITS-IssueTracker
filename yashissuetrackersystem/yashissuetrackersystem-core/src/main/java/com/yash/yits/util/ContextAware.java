package com.yash.yits.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextAware implements ApplicationContextAware{
	
	@Autowired
	private static ApplicationContext applicationContext;
	
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	public void setApplicationContext(ApplicationContext context) throws BeansException {
		
		applicationContext=context;
		
	}

	
	
}

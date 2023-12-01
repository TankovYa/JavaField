package com.tank;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Resource;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;


@Component
public class ProductService implements BeanFactoryAware, InitializingBean, ApplicationListener<ContextClosedEvent>{
	private String beanFactory;
	
	@Resource
	String test = "Resource test";
	
	@Autowired
	private PromotionsService promotionsService;
	
	public PromotionsService getPromotionsService(){
		return promotionsService;
	}
	
	public void setPromotionsService(PromotionsService promotionsService) {
		this.promotionsService = promotionsService;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public void setBeanFactory(String name) {
		beanFactory = name;
	}
	
	public String getBeanFactory() {
		return beanFactory;
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("AfterProperties initializing");
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println(">> ContextClosed EVENT");
	}
}

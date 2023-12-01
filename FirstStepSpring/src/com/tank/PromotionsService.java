package com.tank;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.stereotype.Service;

@Service
public class PromotionsService implements BeanNameAware, DisposableBean{
	private String beanName;
	
	@PostConstruct
	public void testPostConstruct() {
		System.out.println("Test PostConstruct");
	}
	
	@Override
	public void setBeanName(String name) {
		beanName = name;
	}
	
	public String getBeanName() {
		return beanName;
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("PreDestroy " + this);
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy " + this);
	}

}

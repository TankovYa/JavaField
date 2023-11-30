package com.tank;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.stereotype.Service;

@Service
public class PromotionsService implements BeanNameAware{
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

}

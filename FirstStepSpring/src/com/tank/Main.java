package com.tank;

import org.springframework.beans.factory.BeanFactory;

public class Main {

	public static void main(String[] args) {
		new Main();
		
	}
	
	public Main() {
		try {
			testBeanFactory();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	void testBeanFactory() throws ReflectiveOperationException{
		BeanFactory beanFactory = new BeanFactory();
		
		beanFactory.addPostProcessor(new CustomPostProcessor());
		beanFactory.instantiate("com.tank");
		beanFactory.populateProreties();
		beanFactory.injectBeanName();
		beanFactory.injectBeanFactory();
		
		ProductService productService = (ProductService) beanFactory.getBean("productService");
		System.out.println(productService);
		
		PromotionsService promotionsService = productService.getPromotionsService();
		System.out.println(promotionsService);
		
		System.out.println(productService.getTest());
		
		System.out.println("Bean name = " + promotionsService.getBeanName());
		
		System.out.println("Bean factory = " + productService.getBeanFactory());

		beanFactory.initializeBeans();
		
	}
}

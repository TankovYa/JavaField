package com.tank;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.stereotype.Component;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeanFactory beanFactory = new BeanFactory();
		beanFactory.instantiate("com.tank");
	}
	
	@Component
	public class PromotionsService {

	}
	
	@Component
	public class ProductService {
		private PromotionsService promotionsService;
		
		public PromotionsService getPromotionsService(){
			return promotionsService;
		}
		
		public void setPromotionsService(PromotionsService promotionsService) {
			this.promotionsService = promotionsService;
		}
	}
}

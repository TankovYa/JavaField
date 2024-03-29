package org.springframework.context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.event.ContextClosedEvent;

public class ApplicationContext {
	private BeanFactory beanFactory = new BeanFactory();
	
	public ApplicationContext(String basePackage) throws ReflectiveOperationException {
		System.out.println("***Context is under constructor***");
		
		beanFactory.instantiate(basePackage);
		beanFactory.populateProreties();
		beanFactory.injectBeanName();
		beanFactory.initializeBeans();
		
	}
	
	public void close() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		beanFactory.close();
		for(Object bean: beanFactory.getSingletons().values()) {
			if (bean instanceof ApplicationListener) {
				for(Type type: bean.getClass().getGenericInterfaces()) {
					if (type instanceof ParameterizedType) {
						ParameterizedType parameterizedType = (ParameterizedType) type;
						Type firstParameter= parameterizedType.getActualTypeArguments()[0];
						if(firstParameter.equals(ContextClosedEvent.class)) {
							Method method = bean.getClass().getMethod("onApplicationEvent", ContextClosedEvent.class);
							method.invoke(bean, new ContextClosedEvent());
						}
					}
				}
			}
		}
	}
}

package org.springframework.beans.factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Resource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.beans.factory.stereotype.Service;

import com.tank.Main;


public class BeanFactory {
	private Map<String, Object> singletons = new HashMap();
	
	public Map<String, Object> getSingletons() {
		return singletons;
	}

	public void setSingletons(Map<String, Object> singletons) {
		this.singletons = singletons;
	}

	private List<BeanPostProcessor> postProcessors = new ArrayList<>();
	
	public Object getBean(String beanName) {
		return singletons.get(beanName);
	}
	
	public void instantiate(String basePackage) {
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			String path = basePackage.replace('.', '/');
			Enumeration<URL> resources = classLoader.getResources(path);
			
			while(resources.hasMoreElements()) {
				URL resource = resources.nextElement();	
				File file = new File(resource.toURI());
				
				for(File classFile : file.listFiles()) {
					String fileName = classFile.getName();
					System.out.println(fileName);
					
					if(fileName.endsWith(".class")) {
						String className = fileName.substring(0, fileName.lastIndexOf("."));
						Class classObject = Class.forName(basePackage + "." + className);
						
						if(classObject.isAnnotationPresent(Component.class) || classObject.isAnnotationPresent(Service.class)) {
							System.out.println("Component: " + classObject);
							Object instance = classObject.newInstance();
							String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
							singletons.put(beanName, instance);
						}
					} else {
						instantiate(basePackage + "." + fileName);
					}
				}
			}
		} catch (IOException | URISyntaxException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
	}
	
	public void populateProreties() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("--populateProperties--");
		for(Object object: singletons.values()) {
			
			for(Field field : object.getClass().getDeclaredFields()) {
				
				if(field.isAnnotationPresent(Autowired.class)) {
					
					for(Object dependency : singletons.values()) {
						
						if(dependency.getClass().equals(field.getType())) {
							String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
							System.out.println("Setter name = " + setterName);
							Method setter = object.getClass().getMethod(setterName, dependency.getClass());
							setter.invoke(object, dependency);
						}
					}
				} else if(field.isAnnotationPresent(Resource.class)) {
					
					for(Object dependency : singletons.values()) {
						String fieldName = field.getName();
						
						if(dependency.getClass().equals(fieldName)) {
							String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							System.out.println("Setter name = " + setterName);
							Method setter = object.getClass().getMethod(setterName, dependency.getClass());
							setter.invoke(object, dependency);
						}
						
					}
				}
			}
		}
	}
	
	public void injectBeanName() {
		for(String name : singletons.keySet()) {
			
			Object bean = singletons.get(name);
			
			if (bean instanceof BeanNameAware) {
				((BeanNameAware) bean).setBeanName(name);
			}
		}
	}
	
	public void injectBeanFactory() {
		for(Object bean : singletons.values()) {
			
			if (bean instanceof BeanFactoryAware) {
				((BeanFactoryAware) bean).setBeanFactory(this.getClass().getSimpleName());
			}
		}
	}
	
	public void initializeBeans() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(String name : singletons.keySet()) {
			Object bean = singletons.get(name);
			for(Method beanMethod : bean.getClass().getMethods()) {
				if(beanMethod.isAnnotationPresent(PostConstruct.class)) {
					beanMethod.invoke(bean);
				}
			}
			for(BeanPostProcessor postProcessor : postProcessors) {
				postProcessor.postProcessBeforeInitialization(bean, name);
			}
			
			if(bean instanceof InitializingBean){
		        ((InitializingBean) bean).afterPropertiesSet();
		    }
			
			for(BeanPostProcessor postProcessor : postProcessors) {
				postProcessor.postProcessAfterInitialization(bean, name);
			}
		}
	}
	
	public void addPostProcessor(BeanPostProcessor postProcessor) {
		postProcessors.add(postProcessor);
	}
	
	public void close() {
		for(Object bean : singletons.values()) {
			
			for(Method beanMethod : bean.getClass().getMethods()) {
				
				if(beanMethod.isAnnotationPresent(PreDestroy.class)) {
					try {
						beanMethod.invoke(bean);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if (bean instanceof DisposableBean) {
				((DisposableBean) bean).destroy();
			}
			
		}
	}
}

package org.springframework.beans.factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.stereotype.Component;

import com.tank.Main;


public class BeanFactory {
	private Map<String, Object> singletons = new HashMap();
	
	public Object getBean(String beanName) {
		return singletons.get(beanName);
	}
	
	public void instantiate(String basePackage) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String path = basePackage.replace('.', '/');
		try {
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
						if(classObject.isAnnotationPresent(Component.class)) {
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
		} catch (IOException | URISyntaxException | ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
	}
}

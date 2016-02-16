package com.cml.springbatch.interceptor;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-method.xml");

		TestBean bean = context.getBean("testDao", TestBean.class);
		System.out.println(bean.updateName());


	}
}

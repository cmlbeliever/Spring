package com.cml.springbatch.interceptor;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

//MethodInterceptor
public class DaoMethodInteceptor implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("==================after==============" + returnValue);
		if (null == returnValue || ((Integer) returnValue) < 200) {
			throw new RuntimeException("此方法不规范！！！！");
		}
	}

	// @Override
	// public Object invoke(MethodInvocation invocation) throws Throwable {
	// System.out.println("method interceptor===");
	// return invocation.proceed();
	// }

}

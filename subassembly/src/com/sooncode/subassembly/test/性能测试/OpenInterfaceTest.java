package com.sooncode.subassembly.test.性能测试;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.*;

/**
 * 面向接口测试 
 * 测试 方法执行效率  （耗时）
 * 
 * @author pc
 *
 */


public class OpenInterfaceTest implements InvocationHandler {

	private Object obj;

	public OpenInterfaceTest(Object obj) {
		this.obj = obj;
	}

	/**
	 * 代理 创建对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Object newInstance(Object obj) {
		Object result = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new OpenInterfaceTest(obj));
		return (result);
	}

	/**
	 * 执行对象的方法
	 */
	public Object invoke(Object proxy, Method method, Object[] args) {
		Object result;
		try {
			/*
			 * System.out.print("【开始执行】:" + method.getName() + "("); for (int i
			 * = 0; args != null && i < args.length; i++) { if (i > 0)
			 * System.out.print(","); System.out.print(" " +
			 * args[i].toString()); } System.out.println(")");
			 */
			long start = System.nanoTime();
			result = method.invoke(obj, args);
			long end = System.nanoTime();
			System.out.println("【执行方法】 " + method.getName() + " 耗时： " + (end - start) / 1000 + "(ms) / " + (end - start) + "(ns)");
		} catch ( Exception e) {
			 System.out.println("【方法执行异常】： " + e.getMessage()); 
			 return null;
		}  finally {
			// System.out.println("【执行结束】： " + method.getName());
		}
		return result;
	}
}
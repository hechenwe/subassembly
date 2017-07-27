package com.sooncode.subassembly.soontest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import java.lang.reflect.*;

/**
 * 面向接口测试
 * 
 * 
 * @author pc
 *
 */

public class OpenInterfaceTest implements InvocationHandler {

	private static Logger logger = Logger.getLogger(OpenInterfaceTest.class);

	private Object obj;

	public OpenInterfaceTest(Object obj) {
		this.obj = obj;
	}

	/**
	 * 创建对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Object newInstance(Object ImplemtsObject) {
		ClassLoader classLoader = ImplemtsObject.getClass().getClassLoader();
		Class<?>[] interfaces = ImplemtsObject.getClass().getInterfaces();

		Object result = Proxy.newProxyInstance(classLoader, interfaces, new OpenInterfaceTest(ImplemtsObject));
		return (result);
	}

	/**
	 * 测试 方法执行效率 和内存消耗。
	 */
	public Object invoke(Object proxy, Method method, Object[] args) {
		Object result;
		try {
			long startMem = Memory.used();
			long start = System.nanoTime();
			result = method.invoke(obj, args);
			long end = System.nanoTime();
			long endMem = Memory.used();
			double dou = (end - start) / 1000000000.00;
			logger.info("【Jdbc4Json】执行方法  " + method.getName() + "(...) 耗时： " + dou + "(s) / " + (end - start) / 1000000 + "(ms) / " + (end - start) + "(ns)");
			logger.info("【Jdbc4Json】执行方法   " + method.getName() +  "(...) 消耗的内存： " +(endMem - startMem) / (8 * 1024) + "(kB) / "  + (endMem - startMem) / 8 + "(B) / " +  (endMem - startMem) + "(bytes)" );

		} catch (Exception e) {
			logger.info("【Jdbc4Json】：执行方法（"+method.getName()+"） 异常： " + e.getMessage());
			return null;
		} finally {
			// System.out.println("【执行结束】： " + method.getName());
		}
		return result;
	}
}
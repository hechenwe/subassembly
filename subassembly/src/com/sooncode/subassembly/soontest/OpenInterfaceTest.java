package com.sooncode.subassembly.soontest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

 

import java.lang.reflect.*;

/**
 * 面向接口测试
 * 
 * 
 * @author hechenwe@gmail.com
 *
 */

public class OpenInterfaceTest   {
 

	 

	/**
	 * 创建一个用于测试的对象
	 * @param ImplemtsObject
	 * @return 
	 */
	public  <T> T newInstance4Test(Class<T> ImplemtsObjectClass) {
		ClassLoader classLoader = ImplemtsObjectClass.getClassLoader();
		Class<?>[] interfaces = ImplemtsObjectClass.getInterfaces();
        
		Object io;
		try {
			io = ImplemtsObjectClass.newInstance();
			@SuppressWarnings("unchecked")
			T result = (T) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandlerImpl(io));
			return (result);
		} catch ( Exception e) {
			e.printStackTrace();
			return null;
		}  
	}
	/**
	 * 创建一个用于测试的对象
	 * @param ImplemtsObject
	 * @return 
	 */
	public  <T> T newInstance4Test(T ImplemtsObject ) {
		ClassLoader classLoader = ImplemtsObject.getClass().getClassLoader();
		Class<?>[] interfaces = ImplemtsObject.getClass().getInterfaces();
		 
			@SuppressWarnings("unchecked")
			T result = (T) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandlerImpl(ImplemtsObject));
			return (result);
		 
	}

	
	private class InvocationHandlerImpl implements InvocationHandler{

		private Object obj; 
		public InvocationHandlerImpl(Object obj) {
			this.obj = obj ;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result;
			try {
				long startMem =memoryUsed();
				long start = System.nanoTime();
				result = method.invoke(obj, args);
				long end = System.nanoTime();
				long endMem = memoryUsed();
				double dou = (end - start) / 1000000000.00;
				System.out.println("【Jdbc4Json】执行方法  " +this.obj.getClass().getSimpleName()+ "."+ method.getName() + "(...) 耗时： " + dou + "(s) / " + (end - start) / 1000000 + "(ms) / " + (end - start) + "(ns)");
				System.out.println("【Jdbc4Json】执行方法   " +this.obj.getClass().getSimpleName()+ "."+ method.getName() +  "(...) 消耗的内存： " +(endMem - startMem) / (8 * 1024) + "(kB) / "  + (endMem - startMem) / 8 + "(B) / " +  (endMem - startMem) + "(bytes)" );

			} catch (Exception e) {
				System.out.println("【Jdbc4Json】：执行方法（" +this.obj.getClass().getSimpleName()+ "."+ method.getName() +  method.getName()+"） 异常： " + e.getMessage());
				return null;
			} finally {
				//System.out.println("【执行结束】： " + method.getName());
			}
			return result;
		}
		
	}
	
	private static long memoryUsed() {
		long total = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		return (total - free);
	}
	
	
	
	 
}
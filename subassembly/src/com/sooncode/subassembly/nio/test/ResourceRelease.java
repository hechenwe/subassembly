package com.sooncode.subassembly.nio.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 资源释放
 * 
 * @author hechenwe@gmail.com
 *
 */
public class ResourceRelease {

	private static final String METHOD_NAME = "close";

	/**
	 * 关闭资源
	 * @param objects 中的对象存在 close()方法。
	 */
	public static void closes(Object... objects) {
		for (Object object : objects) {
			if (object == null) {
				continue;
			}
		 
				Method method = null;
				for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
					try {
						method = clazz.getDeclaredMethod(METHOD_NAME);
					} catch (Exception e) {
						
					}  
				}
				if (method != null) {
					try {
						method.invoke(object);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}

			 
		}
	}
	
	 
}

package com.sooncode.subassembly.cglib;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Cglib测试类
 * 
 * @author cuiran
 * @version 1.0
 */
public class CglibTest {

	 
	public static void main(String[] args) throws ClassNotFoundException {

		// 设置类成员属性
		HashMap<String,Class<?>> propertyMap = new HashMap<>();

		propertyMap.put("id", Class.forName("java.lang.Integer"));

		propertyMap.put("name", Class.forName("java.lang.String"));

		propertyMap.put("address", Class.forName("java.lang.String"));

		// 生成动态 Bean
		Cglib bean = new Cglib(propertyMap);

		// 给 Bean 设置值
		bean.setValue("id", new Integer(123));

		bean.setValue("name", "454");

		bean.setValue("address", "789");

		// 从 Bean 中获取值，当然了获得值的类型是 Object

		System.out.println("  >> id      = " + bean.getValue("id"));

		System.out.println("  >> name    = " + bean.getValue("name"));

		System.out.println("  >> address = " + bean.getValue("address"));

		// 获得bean的实体
		Object object = bean.getObject();

		// 通过反射查看所有方法名
		Class<?> clazz = object.getClass();
		System.out.println("CglibTest.main()"+clazz.getName());
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
		}
		
		Object obj;
		try {
			obj = clazz.newInstance();
			System.out.println(obj);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
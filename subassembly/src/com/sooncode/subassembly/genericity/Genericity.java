package com.sooncode.subassembly.genericity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型
 * @author pc
 *
 */
public class Genericity {

	public static String  getGenericity(Class<?> thisClass,int index){
		
		Type type = thisClass.getGenericSuperclass(); // 非常关键的一步
		ParameterizedType parameterizedType = (ParameterizedType) type;// ParameterizedType:表示参数化类型
		Class<?> TClass = (Class<?>) parameterizedType.getActualTypeArguments()[index]; // getActualTypeArguments():
		String tClassName = TClass.getName(); // 泛型T实际运行时的全类名
		
		return tClassName;
	}
}

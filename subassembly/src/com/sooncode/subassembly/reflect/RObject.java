package com.sooncode.subassembly.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 反射创建的对象
 * 
 * @author pc
 *
 */
public class RObject {
	public static Logger logger = Logger.getLogger("RObject.class");
	/** 被反射代理的对象 */
	private Object object;
	// private Class<?> clas;

	/** 获取对象的类名 */
	public String getClassName() {
		// String[] str = this.object.getClass().getName().split("\\.");
		return this.object.getClass().getSimpleName();// str[str.length -
														// 1].trim();
	}

	/** 获取对象的全类名 */
	public String getAllClassName() {
		return this.object.getClass().getName();
	}

	/** 获取被反射代理的对象 */
	public Object getObject() {
		return object;
	}

	/**
	 * 获取被反射代理对象的属性集(除serialVersionUID属性外)
	 * 
	 * @return
	 */
	public List<Field> getFields() {
		List<Field> list = new ArrayList<>();
		Field[] fields = this.object.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (!f.getName().equals("serialVersionUID")) {
				list.add(f);
			}
		}
		return list;
	}

	public RObject(Object object) {
		this.object = object;
		Map<String, Object> fields = this.getFiledAndValue();
		for (Map.Entry<String, Object> entry : fields.entrySet()) {
			this.invokeSetMethod(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 执行对象的SET方法
	 * 
	 * @param fieldName
	 * @param args
	 */
	public void invokeSetMethod(String fieldName, Object... args) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, this.object.getClass());
			// 获得set方法
			Method method = pd.getWriteMethod();
			method.invoke(object, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();

		}

	}

	/**
	 * 获取Set方法的参数类型
	 * 
	 * @param fieldName
	 * @return
	 */
	public Class<?> getSetMethodParamertType(String fieldName) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, this.object.getClass());
			Method method = pd.getWriteMethod();
			Class<?>[] c = method.getParameterTypes();
			return c[0];
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 执行对象的GET方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public Object invokeGetMethod(String fieldName) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, this.object.getClass());
			// 获得set方法
			Method method = pd.getReadMethod();
			return method.invoke(this.object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}

	}

	/** 获取对象的属性和其对应的值 */
	public Map<String, Object> getFiledAndValue() {
		Map<String, Object> map = new HashMap<>();
		Class<?> c = object.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equals("serialVersionUID")) {
				map.put(field.getName(), this.invokeGetMethod(field.getName()));
			}
		}
		return map;
	}
	
    /**
     * 反射执行方法
     * @param methodName 方法名称
     * @param args 方法需要的参数集
     * @return 方法执行的返回值
     */
	public Object invoke(String methodName,Object...args) {
		try {
			Method method = this.object.getClass().getMethod(methodName);
			return method.invoke(object,args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

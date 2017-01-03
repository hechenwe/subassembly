package com.sooncode.subassembly.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
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
	private static final String NULL_STR = "";
	private static final String CLASS = "class ";

	private Map<String, Field> fieldMap = new HashMap<>();
	private List<Field> fields;

	/** 被反射代理的对象 */
	private Object object;

	public <T> RObject(T object) {
		this.object = object;
		this.initFields();
	}

	public RObject(Class<?> clas) {

		try {
			this.object = clas.newInstance();
			this.initFields();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RObject(String className) {
		Class<?> clas;
		try {
			clas = Class.forName(className);
			this.object = clas.newInstance();
			this.initFields();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取对象的类名 */
	public String getSimpleName() {
		return this.object.getClass().getSimpleName();
	}

	/** 获取对象的全类名 */
	public String getClassName() {
		return this.object.getClass().getName();
	}

	/** 获取被反射代理的对象 */
	public <T> T getObject() {
		@SuppressWarnings("unchecked")
		T t = (T) object;
		return t;
	}

	/**
	 * 
	 * 初始化代理对象的属性
	 * 
	 * @return
	 */
	public void initFields() {
		List<Field> list = new ArrayList<>();

		Class<?> thisClass = this.object.getClass();

		for (; thisClass != Object.class; thisClass = thisClass.getSuperclass()) {

			Field[] fields = thisClass.getDeclaredFields();

			if (thisClass == this.object.getClass()) {
				for (Field field : fields) {
					list.add(field);
					this.fieldMap.put(field.getName(), field);
				}

			} else {
				for (Field field : fields) {
					int i = field.getModifiers();
					boolean isPrivate = Modifier.isPrivate(i);
					if (isPrivate == false) {
						list.add(field);
						this.fieldMap.put(field.getName(), field);
					}
				}
			}
		}
		this.fields = list;

	}

	/**
	 * 判断属性是否存在
	 * 
	 * @param field
	 * @return
	 */
	public Boolean hasField(String fieldName) {

		if (fieldName == null || fieldName.equals(NULL_STR)) {
			return false;
		}
		Field f = this.fieldMap.get(fieldName);

		return f == null ? false : true;

	}

	/**
	 * 获取 list 类型的属性名称
	 * 
	 * @param parameterizedType
	 *            属性的参数类型Class
	 * 
	 * @return 属性集
	 */
	public List<Field> getFields(Class<?> parameterizedType) {

		for (Field f : this.fields) {
			Class<?> fClass = f.getType();
			if (fClass == parameterizedType) {
				ParameterizedType pt = (ParameterizedType) f.getGenericType();
				String str = pt.getActualTypeArguments()[0].toString(); // 获取List泛型参数类型名称
				str = str.replace(CLASS, NULL_STR).trim();// 全类名
				// if (str.equals(listClass.getName())) {
				// return f.getName();
				// }
			}
		}
		return null;
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
		} catch (Exception e) {
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
			return null;
		}

	}

	/**
	 * 执行对象的GET方法
	 * 
	 * @param fieldName
	 * @return
	 */

	public <T> T invokeGetMethod(String fieldName) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, this.object.getClass());
			// 获得set方法
			Method method = pd.getReadMethod();
			@SuppressWarnings("unchecked")
			T t = (T) method.invoke(this.object);
			return t;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

	}

	/** 获取对象的属性和其对应的值 */
	public Map<String, Object> getFiledAndValue() {

		Map<String, Object> map = new HashMap<>();
		List<Field> fields = this.getFields();
		for (Field field : fields) {
			String name = field.getName().replace("$cglib_prop_", "");
			map.put(name, this.invokeGetMethod(name));
		}
		return map;
	}

	/**
	 * 反射执行方法
	 * 
	 * @param methodName
	 *            方法名称
	 * @param args
	 *            方法需要的参数集
	 * @return 方法执行的返回值
	 */

	public <T> T invoke(String methodName, Object... args) {
		try {
			Method method = null;
			for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				try {
					method = clazz.getDeclaredMethod(methodName, new Object().getClass());
				} catch (Exception e) {
				}
			}
			@SuppressWarnings("unchecked")
			T t = (T) method.invoke(object, args);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Method getDeclaredMethod(Object object, String methodName) {
		Method method = null;
		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName);
				return method;
			} catch (Exception e) {
			}
		}
		return null;
	}

	public List<Field> getFields() {
		return fields;
	}

}

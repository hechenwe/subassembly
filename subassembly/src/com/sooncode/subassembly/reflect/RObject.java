package com.sooncode.subassembly.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 反射创建的对象
 * 
 * @author hechenwe@gmail.com
 *
 */
public class RObject<T> {

	public final static Log logger = LogFactory.getLog(RObject.class);
	private static final String NULL_STR = "";
	private static final String CLASS = "class ";
	private static final String LIST_INTERFACE = "interface java.util.List";
	private static final String JAVA_TYPES = "Integer Long Short Byte Float Double Character Boolean Date String";
	private static final String UID = "serialVersionUID";

	/** 被反射代理的对象 */
	private T object;

	private Map<String, Field> fields;

	public RObject(T object) {
		this.object = object;
		init();
	}

	public RObject(Class<T> clas) {
		try {
			this.object = (T) clas.newInstance();
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public RObject(String className) {
		Class<T> clas;
		try {
			clas = (Class<T>) Class.forName(className);
			this.object = clas.newInstance();
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取被反射代理的对象 */
	public T getObject() {
		return object;
	}

	/**
	 * 获取被反射代理对象的属性集(除serialVersionUID属性外) 包括父类的属性
	 * 
	 * @return
	 */
	public List<Field> getFields() {
		Collection<Field> collection = fields.values();
		List<Field> list = new ArrayList<>(collection);
		return list;
	}

	private void init() {

		Map<String, Field> map = new HashMap<>();
		Class<?> thisClass = this.object.getClass();
		boolean isThisClass = true;
		for (; thisClass != Object.class; thisClass = thisClass.getSuperclass()) {
			Field[] fields = thisClass.getDeclaredFields();
			for (Field f : fields) {
				boolean isNotUID = !f.getName().equals(UID);
				if (isNotUID) {
					if (isThisClass) {
						map.put(f.getName(), f);
					} else {
						int i = f.getModifiers();
						boolean isPrivate = Modifier.isPrivate(i);
						if (isPrivate == false) {
							map.put(f.getName(), f);
						}
					}
				}
			}
			isThisClass = false;
		}

		fields = map;
	}

	/**
	 * 判断属性是否存在
	 * 
	 * @param fieldName 
	 * @return true/false
	 */
	public boolean hasField(String fieldName) {
		return fields.containsKey(fieldName);

	}

	/**
	 * 获取 list 类型的属性名称
	 * 
	 * @param listClass
	 *            list的数据类型
	 * @return 属性名称
	 */
	public String getListFieldName(Class<?> listClass) {
		List<Field> fields = getFields();
		for (Field f : fields) {
			String typeName = f.getType().toString();
			if (typeName.contains(LIST_INTERFACE)) {
				ParameterizedType pt = (ParameterizedType) f.getGenericType();
				String str = pt.getActualTypeArguments()[0].toString(); // 获取List泛型参数类型名称
				str = str.replace(CLASS, NULL_STR).trim();// 全类名
				if (str.equals(listClass.getName())) {
					return f.getName();
				}
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

		if (this.hasField(fieldName)) {
			PropertyDescriptor pd;
			try {
				pd = new PropertyDescriptor(fieldName, this.object.getClass());
				Method method = pd.getWriteMethod();
				method.invoke(object, args);
			} catch (Exception e) {
			}
		}

	}

	/**
	 * 获取Set方法的参数类型
	 * 
	 * @param fieldName
	 * @return
	 */
	public Class<?> getSetMethodParamertType(String fieldName) {
		if (this.hasField(fieldName)) {
			PropertyDescriptor pd;
			try {
				pd = new PropertyDescriptor(fieldName, this.object.getClass());
				Method method = pd.getWriteMethod();
				Class<?>[] c = method.getParameterTypes();
				return c[0];
			} catch (IntrospectionException e) {
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * 执行对象的GET方法
	 * 
	 * @param fieldName
	 * @return
	 */

	public T invokeGetMethod(String fieldName) {
		if ( ! this.hasField(fieldName) ) {
			return null;
		}
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

		String str = JAVA_TYPES;
		Map<String, Object> map = new HashMap<>();

		List<Field> fields = this.getFields();
		for (Field field : fields) {
			String name = field.getName();
			if (!field.getName().equals(UID) && str.contains(field.getType().getSimpleName())) {
				map.put(name, this.invokeGetMethod(name));
			}
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

	public T invoke(String methodName, Object... args) {
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

	public Method getDeclaredMethod(String methodName) {
		Method method = null;
		for (Class<?> clazz = this.object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName);
				return method;
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 实体转换
	 * 
	 * @param oldObj
	 * @param newObjClass
	 * @return
	 */
	public static <N, O> N to(O oldObj, Class<N> newObjClass) {
		if (oldObj == null || newObjClass == null) {
			return null;
		}
		RObject<O> rOldObj = new RObject<>(oldObj);
		RObject<N> rNewObj = new RObject<>(newObjClass);

		Map<String, Object> map = rOldObj.getFiledAndValue();

		for (Entry<String, Object> en : map.entrySet()) {
			String key = en.getKey();
			Object val = en.getValue();
			if (rNewObj.hasField(key)) {
				rNewObj.invokeSetMethod(key, val);
			}
		}

		return rNewObj.getObject();

	}

	/**
	 * 批量对象转换
	 * 
	 * @param oldObjes
	 *            被转换的对象集
	 * @param newObjClass
	 *            新对象的Class
	 * @return 新对象集
	 */
	public static <N, O> List<N> tos(List<O> oldObjes, Class<N> newObjClass) {
		List<N> list = new ArrayList<N>();
		if (oldObjes == null || oldObjes.size() == 0 || newObjClass == null) {
			return new ArrayList<>();
		}
		for (O obj : oldObjes) {
			N newObje = to(obj, newObjClass);
			list.add(newObje);
		}
		return list;
	}

}

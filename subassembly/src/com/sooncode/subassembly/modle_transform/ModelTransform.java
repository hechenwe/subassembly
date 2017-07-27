package com.sooncode.subassembly.modle_transform;

 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sooncode.subassembly.reflect.RObject;

/**
 * 反射创建的对象
 * 
 * @author pc
 *
 */
public class ModelTransform {
	  
	/**
	 * 对象转换 注意 对象中的属性均是基本数据类：Integer Long Short Byte Float Double Character
	 * Boolean Date String
	 * 
	 * @param oldObj
	 *            被转换的对象
	 * @param newObjClass
	 *            新对象的Class
	 * @return 新对象
	 * 
	 */
	public static Object to(Object oldObj, Class<?> newObjClass) {
        if(oldObj==null || newObjClass==null){
        	return null;
        }
		RObject rOldObj = new RObject(oldObj);
		RObject rNewObj = new RObject(newObjClass);

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
	public static List<?> tos(List<?> oldObjes, Class<?> newObjClass) {
		List<Object> list = new ArrayList<Object>();
		  if(oldObjes==null || oldObjes.size()==0 || newObjClass==null){
	        	return new ArrayList<>();
	        }
		for (Object obj : oldObjes) {
			Object newObje = to(obj, newObjClass);
			list.add(newObje);
		}
		return list;
	}

	 

}

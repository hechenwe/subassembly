package com.sooncode.subassembly.dictionary.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.jdbc.config.DB;
import com.sooncode.subassembly.dictionary.jdbc.reflect.RClass;
import com.sooncode.subassembly.dictionary.jdbc.reflect.RObject;
import com.sooncode.subassembly.dictionary.jdbc.util.Pager;
import com.sooncode.subassembly.dictionary.jdbc.util.Table2Entity;


/**
 * Dao 基类
 * 
 * @author pc
 * 
 */
public abstract class Dao<T> {

	public final static Logger logger = Logger.getLogger("Dao.class");
 
	/**
	 * 数据源
	 * 
	 * @return
	 */
	public static Connection getCollection() {
		return DB.getConnection();
	}

	/**
	 * 获取一个实体对象集
	 * 
	 * @param obj
	 * @return
	 */

	public List<T> gets(Object obj) {
		// 去数据库查询
		String sql = new Sql().select(obj).toString();
		List<Map<String, Object>> list = Jdbc.executeQuery(getCollection(), sql);
		return getObjectList(list);
	}
	
	/**
	 * 获取一个实体(逻辑上只有一个匹配的实体存在)
	 * 
	 * @param obj 封装的查询条件
	 * @return 实体
	 */
	public T get(Object obj) {
		 
		
		// 去数据库查询
		String sql = new Sql().select(obj).toString();
		List<Map<String, Object>> list = Jdbc.executeQuery(getCollection(), sql);
		if(list.size()==1){
			return getObjectList(list).get(0);
		}else{
			return null;
		}
	}

	/**
	 * 获取分析对象
	 * 
	 * @param obj
	 *            查询条件对象
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页长度
	 * @return Pager 对象
	 */
	public Pager<?> getPager(Object obj, Long pageNum, Long pageSize) {
		// 去数据库查询
		String sql = new Sql().select(obj).limit(pageNum, pageSize).toString();
		List<Map<String, Object>> list = Jdbc.executeQuery(getCollection(), sql);
		Long size = getSize(obj);
		Pager<?> pager = new Pager<>(pageNum, pageSize, size, getObjectList(list));
		return pager;
	}

	public Long getSize(Object object) {
		String sql = new Sql().selectSize(object).toString();
		List<Map<String, Object>> list = Jdbc.executeQuery(getCollection(), sql);
		if (list.size() == 1) {
			Object obj = list.get(0).get("SIZE");
			return (Long) obj;
		} else {
			return null;
		}

	}

	/**
	 * 保存一个实体对象
	 * 
	 * @param object
	 * @return 保存数量
	 */
	public Long save(Object object) {
		// 验证obj
		if (isNull(object) == false) {
			return null;
		}
		String sql = new Sql().insert(object).toString();

		Long n =  Jdbc.executeUpdate(getCollection(), sql);

		return n;

	}

	/**
	 * 修改一个实体对象
	 * 
	 * @param object
	 * @return 更新数量
	 */
	public int update(Object object) {
		if (isNull(object) == false) {
			return 0;
		}
		String sql = new Sql().update(object).toString();
		int n = new Long(Jdbc.executeUpdate(getCollection(), sql)).intValue();
		return n;

	}

	/**
	 * 移除一个实体对象
	 * 
	 * @param object
	 * @return 删除数量
	 */
	public int delete(Object object) {
		if (isNull(object) == false) {
			return 0;
		}
		String sql = new Sql().delete(object).toString();
		int n = new Long(Jdbc.executeUpdate(getCollection(), sql)).intValue();
		return n;

	}

	/**
	 * 将结果集封装成实体集
	 * 
	 * @param list
	 *            执行SQL语句得到的结果集
	 * @return 封装好的实体集
	 */
	@SuppressWarnings("unchecked")
	public List<T> getObjectList(List<Map<String, Object>> list) {
		// -----------------------------
		Class<?> thisClass = this.getClass();
		// Class.getGenericSuperclass()方法在java6 API中表述为:返回表示此 Class
		// 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type(Type理解为: 参数化类型)。
		// 所以本类一定要被继承(或者继承其他含有泛型的类,但是在本类中一定会指定泛型的类型,然后再在本类中用这种办法获取类型就没有必要了),
		// 才能获取该类运行时的泛型的实际类型
		Type type = thisClass.getGenericSuperclass(); // 非常关键的一步
		ParameterizedType parameterizedType = (ParameterizedType) type;// ParameterizedType:表示参数化类型
																		// 继承了Type接口
		Class<?> TClass = (Class<?>) parameterizedType.getActualTypeArguments()[0]; // getActualTypeArguments():
		// 返回表示此类型实际类型参数的 Type 对象的数组。 Class 实现了Type接口
		String TClassName = TClass.getName(); // 泛型T实际运行时的全类名
		// -----------------------------
		RClass rClass = new RClass(TClassName);
		List<Field> fields = rClass.getFields();
		List<T> objects = new ArrayList<>();
		for (Map<String, Object> map : list) {

			RObject rObject = rClass.getRObject();
			for (int i = 0; i < fields.size(); i++) {
				String fieldName =fields.get(i).getName(); 
				Object o = map.get(Table2Entity.field2Column(fieldName));
				rObject.invokeSetMethod(fields.get(i).getName(), o);
			}
			objects.add((T) rObject.getObject());
		}
		return objects;

	}

	/**
	 * 将结果集封装成实体集
	 * 
	 * @param list
	 *            执行SQL语句得到的结果集
	 * @return 封装好的实体集
	 */
	@SuppressWarnings("unchecked")
	public T getMany2Many(List<Map<String, Object>> list) {
		//验证 list 参数 
        if(list == null || list.size()==0){
        	return null;
        }
		// -------------反射获取泛型 T ----------------
		Class<?> thisClass = this.getClass();
		Type type = thisClass.getGenericSuperclass(); // 非常关键的一步
		ParameterizedType parameterizedType = (ParameterizedType) type;// ParameterizedType:表示参数化类型
		Class<?> TClass = (Class<?>) parameterizedType.getActualTypeArguments()[0]; // getActualTypeArguments():
		String TClassName = TClass.getName(); // 泛型T实际运行时的全类名
		// -----------------------------
		RClass rClass = new RClass(TClassName);
		List<Field> fields = rClass.getFields();

		Map<String, Object> map = list.get(0);
		String rightClassName = "";

		for (Map.Entry<String, Object> en : map.entrySet()) {
			String str = en.getKey();
			String[] strs = str.split("_");
			String className = strs[0];
			if (!rClass.getClassName().toUpperCase().contains(className)) {
				rightClassName = className;  //获取 右实体对应的 类名 (大写)
				break;
			}
		}

		RObject leftObjet = rClass.getRObject();
		for (Field f : fields) {
			Object obj = map.get(TClass.getSimpleName().toUpperCase() + "_" + Table2Entity.field2Column(f.getName()));
			if (obj != null) {
				leftObjet.invokeSetMethod(f.getName(), obj); //给左实体对应的类对象的属性赋值
			}
		}

		logger.info(leftObjet.getObject());

		Map<String, String> rightFields = new HashMap<String, String>();
		Map<String, String> listFields = new HashMap<String, String>();

		for (Field f : fields) {
			if (f.getType().toString().contains("interface java.util.List")) {  
				ParameterizedType pt = (ParameterizedType) f.getGenericType();
				String str = pt.getActualTypeArguments()[0].toString(); //获取List泛型参数类型名称 (第一个参数)
				str = str.replace("class ", "").trim();//全类名
				rightFields.put(f.getType().getSimpleName().toUpperCase(), str); 
				listFields.put(f.getType().getSimpleName().toUpperCase(), f.getName()); //属性名称
			}
		}

		String rightClass = rightFields.get(rightClassName);//全类名
		logger.info(rightClass);
		List<Object> rightObjects = new ArrayList<>();
        //获取 右端 实体集 
		for (Map<String, Object> map2 : list) { 
			
			RClass rightCl = new RClass(rightClass);
			RObject rightO = rightCl.getRObject();

			List<Field> fs = rightCl.getFields();
			for (Field f : fs) {

				Object o = map2.get(rightClassName.toUpperCase() + "_" + Table2Entity.field2Column(f.getName()));

				if (o != null) {
					rightO.invokeSetMethod(f.getName(), o);
				}

			}
			rightObjects.add(rightO.getObject());
		}
        
		String listField = listFields.get(rightClassName);
		leftObjet.invokeSetMethod(listField, rightObjects); //
		return (T) leftObjet.getObject();

	}

	/**
	 * 将结果集封装成实体集
	 * 
	 * @param list
	 *            执行SQL语句得到的结果集
	 * @return 封装好的实体集
	 */

	@SuppressWarnings("unchecked")
	public List<T> getOne2One(List<Map<String, Object>> list) {
        //基本的数据类型
		String str = "Integer Long Short Byte Float Double Character Boolean Date String List";
		// -----------------------------
		Class<?> thisClass = this.getClass();
		Type type = thisClass.getGenericSuperclass(); // 非常关键的一步
		ParameterizedType parameterizedType = (ParameterizedType) type;// ParameterizedType:表示参数化类型
		Class<?> TClass = (Class<?>) parameterizedType.getActualTypeArguments()[0]; // getActualTypeArguments():
		String TClassName = TClass.getName(); // 泛型T实际运行时的全类名
		// -----------------------------
		RClass rClass = new RClass(TClassName);
		List<Object> resultList = new ArrayList<>();

		for (Map<String, Object> m : list) { // 遍历数据库返回的数据集合
			RObject rObject = rClass.getRObject();
			List<Field> fields = rObject.getFields();// T 对应的属性

			for (Field f : fields) {
				Object obj = m.get(TClass.getSimpleName().toUpperCase() + "_" + Table2Entity.field2Column(f.getName()));
				if (obj != null) {
					rObject.invokeSetMethod(f.getName(), obj);
				}

				String typeSimpleName = f.getType().getSimpleName();
				String typeName = f.getType().getName();
				if (!str.contains(typeSimpleName)) {
					RClass rC = new RClass(typeName);
					RObject rO = rC.getRObject();

					List<Field> fs = rC.getFields();

					for (Field ff : fs) {

						Object o = m.get(typeSimpleName.toUpperCase() + "_" + Table2Entity.field2Column(ff.getName()));
						if (o != null) {
							rO.invokeSetMethod(ff.getName(), o);
						}

					}

					rObject.invokeSetMethod(f.getName(), rO.getObject());
				}
			}

			resultList.add(rObject.getObject());

		}

		return (List<T>) resultList;

	}

	/**
	 * 验证 object是否为空 或 其属性是否全为空
	 * 
	 * @param object 被验证的实体
	 * @return 
	 */
	private boolean isNull(Object object) {
		if (object == null) {
			return false;
		}
		// obj的属性值不全为null
		RObject rObj = new RObject(object);
		Map<String, Object> files = rObj.getFiledAndValue();
		boolean b = false;
		for (Map.Entry<String, Object> en : files.entrySet()) {
			if (en.getValue() == null) {
				b = b || false;
			} else {
				b = b || true;
			}
		}

		if (b == false) {
			return false;
		} else {
			return true;
		}
	}

}

package com.sooncode.subassembly.dictionary.jdbc;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

 
import com.sooncode.subassembly.dictionary.jdbc.reflect.RClass;
import com.sooncode.subassembly.dictionary.jdbc.reflect.RObject;
import com.sooncode.subassembly.dictionary.jdbc.util.Table2Entity;
 
/**
 * SQL工具类 注意: 对象的类名 与 数据库表名"一致"
 * 
 * @author pc
 *
 */
public class Sql {

	private static Logger logger = Logger.getLogger("Sql.class");
	/**
	 * 可执行的SQL
	 */
	private String sqlString;

	public String toString() {
		return sqlString;
	}

	/**
	 * 构造插入数据的可执行的SQL 说明 :1.根据object对象的类名映射成数据库表名.
	 * 2.根据object对象的属性,映射成字段,根据其属性值插入相应数据.
	 * 
	 * @param object
	 *            数据对象
	 * @return 可执行SQL
	 */
	public Sql insert(Object object) {
		RObject rObject = new RObject(object);
		String tableName = Table2Entity.field2Column(rObject.getClassName());
		Map<String, Object> map = rObject.getFiledAndValue();
		String columnString = "(";
		String filedString = "(";
		int n = 0;
		for (Map.Entry<String, Object> entry : map.entrySet()) {

			columnString = columnString + Table2Entity.field2Column(entry.getKey());
			if (entry.getValue() == null) {
				filedString = filedString + "NULL";
			} else {

				if (entry.getValue().getClass().getName().equals("java.util.Date")) {
					filedString = filedString + "'" + new SimpleDateFormat("yyyy-MM-dd HH-MM-ss").format(entry.getValue()) + "'";
				} else {
					filedString = filedString + "'" + entry.getValue() + "'";
				}
			}
			if (n != map.size() - 1) {
				columnString += ",";
				filedString += ",";
			} else {
				columnString += ")";
				filedString += ")";
			}
			n++;

		}
		String sqlString = "INSERT INTO " + tableName + columnString + " VALUES " + filedString;
		this.sqlString = sqlString;
		return this;
	}

	/**
	 * 删除
	 * 
	 * @param object
	 * @return
	 */
	public Sql delete(Object object) {
		RClass rClass = new RClass(object);
		String tableName = Table2Entity.field2Column(rClass.getClassName());
		String sql = "DELETE FROM " + tableName + " WHERE ";
		RObject rObject = new RObject(object);
		Map<String, Object> map = rObject.getFiledAndValue();
		String s = "";
		int n = 0;
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				if (n != 0) {
					s = s + " AND ";
				}
				s = s + Table2Entity.field2Column(entry.getKey()) + "='" + entry.getValue() + "'";
				n++;
			}
		}
		sql = sql + s;
		this.sqlString = sql;
		return this;

	}

	/** 获取修改数据的SQL */
	public Sql update(Object obj) {
		RClass rClass = new RClass(obj);
		String tableName = Table2Entity.field2Column(rClass.getClassName());
		RObject rObject = new RObject(obj);
		Map<String, Object> map = rObject.getFiledAndValue();
		int n = 0;
		String s = "";
		String pkString = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			if (n == 0) {
				pkString = Table2Entity.field2Column(entry.getKey()) + "='" + entry.getValue() + "'";
			}
			if (entry.getValue() != null) {
				if (n != 0) {
					s = s + " , ";
				}
				s = s + Table2Entity.field2Column(entry.getKey()) + "='" + entry.getValue() + "'";
				n++;
			}
		}

		String sql = "UPDATE " + tableName + "  SET  " + s + " WHERE " + pkString;

		this.sqlString = sql;
		return this;
	}

	/**
	 * 获取查询语句的可执行SQL
	 * 
	 * @param object
	 * @return 可执行SQL
	 */
	public Sql select(Object object) {
		RClass rClass = new RClass(object);
		String tableName = Table2Entity.field2Column(rClass.getClassName());
		RObject rObject = new RObject(object);
		Map<String, Object> map = rObject.getFiledAndValue();
		int m = 0;
		String s = "1=1";
		String c = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				s = s + " AND ";
				s = s + Table2Entity.field2Column(entry.getKey()) + "='" + entry.getValue() + "'";
			}
			if (m != 0) {
				c = c + ",";
			}
			c = c +Table2Entity. field2Column(entry.getKey());
			m++;
		}
		String sql = "SELECT " + c + " FROM " + tableName + " WHERE " + s;
		//logger.info("可执行SQL语句:" + sql);
		this.sqlString = sql;
		return this;
	}
	 

	/**
	 * 获取记录的条数的可执行SQL
	 * 
	 * @param object
	 * @return 可执行SQL
	 */
	public Sql selectSize(Object object) {
		RClass rClass = new RClass(object);
		String tableName = Table2Entity.field2Column(rClass.getClassName());
		RObject rObject = new RObject(object);
		Map<String, Object> map = rObject.getFiledAndValue();
		int m = 0;
		String s = "1=1";
		String c = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				s = s + " AND ";
				s = s + Table2Entity.field2Column(entry.getKey()) + "='" + entry.getValue() + "'";
			}
			if (m != 0) {
				c = c + ",";
			}
			c = c + Table2Entity.field2Column(entry.getKey());
			m++;
		}
		String sql = "SELECT COUNT(1) AS SIZE" + " FROM " + tableName + " WHERE " + s;
		logger.info("可执行SQL语句:" + sql);
		this.sqlString = sql;
		return this;

	}

	public Sql eq(String fieldName, Object value) {

		String sql = " AND " + Table2Entity.field2Column(fieldName) + "='" + value + "'";
		this.sqlString = this.sqlString + sql;
		return this;
	}

	public Sql frontLike(String fieldName, Object value) {

		String sql = " AND " + Table2Entity.field2Column(fieldName) + " LIKE '%" + value + "'";
		this.sqlString = this.sqlString + sql;
		return this;
	}

	public Sql behindLike(String fieldName, Object value) {

		String sql = " AND " + Table2Entity.field2Column(fieldName) + " LIKE '" + value + "%'";
		this.sqlString = this.sqlString + sql;
		return this;
	}

	public Sql like(String fieldName, Object value) {

		String sql = " AND " + Table2Entity.field2Column(fieldName) + " LIKE '%" + value + "%'";
		this.sqlString = this.sqlString + sql;
		return this;
	}

	public Sql between(String fieldName, Object value1, Object value2) {

		String sql = " AND " + Table2Entity.field2Column(fieldName) + " BETWEEN '" + value1 + "' AND '" + value2 + "'";
		this.sqlString = this.sqlString + sql;
		return this;
	}

	/**
	 * 分页功能
	 * 
	 * @param pageNumber
	 *            第几页
	 * @param pageSize
	 *            每页长度
	 * @return Sql 对象
	 */
	public Sql limit(Long pageNumber, Long pageSize) {
		Long index = (pageNumber - 1) * pageSize;
		String sql = " LIMIT " + index + "," + pageSize;
		this.sqlString = this.sqlString + sql;
		return this;

	}

	
	
	/**
	 * 获取多对多映射的可执行SQL
	 * 
	 * @param left 主表对应的实体类 
	 * @param middle 中间表对应的实体类 
	 * @param right N端对应的实体类
	 * @return 可执行SQL
	 */
	public Sql getMany2Many(Object left, Object middle, Object right) {

		String leftTable = Table2Entity.field2Column(left.getClass().getSimpleName());
		String middleTable = Table2Entity.field2Column(middle.getClass().getSimpleName());
		String rightTable = Table2Entity.field2Column(right.getClass().getSimpleName());

		RObject leftRObject = new RObject(left);
		RObject rightRObject = new RObject(right);

		String leftPk = Table2Entity.field2Column(leftRObject.getPk());
		String rightPk = Table2Entity.field2Column(rightRObject.getPk());
		Map<String, Object> leftFileds = leftRObject.getFiledAndValue();
		Map<String, Object> rightFileds = rightRObject.getFiledAndValue();

		String col = "";
		int n = 0;
		for (Map.Entry<String, Object> en : leftFileds.entrySet()) {
			if (n != 0) {
				col = col + " , ";
			}
			col = col + leftTable + "." + Table2Entity.field2Column(en.getKey()) + " AS " + leftTable + "_" + Table2Entity.field2Column(en.getKey());
			n++;
		}
		for (Map.Entry<String, Object> en : rightFileds.entrySet()) {

			col = col + " , " + rightTable + "." + Table2Entity.field2Column(en.getKey()) + " AS " + rightTable + "_" + Table2Entity.field2Column(en.getKey());

		}
		String sql = "SELECT " + col + " FROM " + leftTable + " ," + middleTable + " , " + rightTable;

		sql = sql + " WHERE " + leftTable + "." + leftPk + " = " + middleTable + "." + leftPk + " AND " + rightTable + "." + rightPk + " = " + middleTable + "." + rightPk + " AND " + leftTable + "." + leftPk + "='" + leftFileds.get(Table2Entity.column2field(leftPk)) + "'";
		this.sqlString = sql;

		return this;
	}

	/**
	 * 获取 一对一模型的可执行SQL
	 * 
	 * @param left 被参照表对应的实体类
	 * @param other 其他参照表对应的实体类 ,至少有一个实体类
	 * @return 可执行SQL 
	 */
	public Sql getOne2One(Object left, Object... other) {

		String leftTable = Table2Entity.field2Column(left.getClass().getSimpleName());
		RObject leftRObject = new RObject(left);
		Map<String, Object> leftFileds = leftRObject.getFiledAndValue();
		String col = "";
		int n = 0;
		for (Map.Entry<String, Object> en : leftFileds.entrySet()) {
			if (n != 0) {
				col = col + ",";
			}
			col = col + " " + leftTable + "." + Table2Entity.field2Column(en.getKey()) + " AS " + leftTable + "_" + Table2Entity.field2Column(en.getKey());
			n++;
		}

		Map<String, String> map = new HashMap<>();

		for (Object obj : other) {

			String table = Table2Entity.field2Column(obj.getClass().getSimpleName());
			RObject rObject = new RObject(obj);
			String pk = Table2Entity.field2Column(rObject.getPk());
			map.put(table, pk);
			Map<String, Object> field = rObject.getFiledAndValue();

			for (Map.Entry<String, Object> en : field.entrySet()) {
				col = col + "," + table + "." + Table2Entity.field2Column(en.getKey()) + " AS " + table + "_" +Table2Entity. field2Column(en.getKey());
			}
		}

		String where = "";
		String from = " " + leftTable;
		int m = 0;
		for (Map.Entry<String, String> en : map.entrySet()) {
			if (m != 0) {
				where = where + " AND ";
			}
			where = where + leftTable + "." + en.getValue() + " = " + en.getKey() + "." + en.getValue();
			from = from + "," + en.getKey();
			m++;
		}

		String sql = "SELECT " + col + " FROM " + from + " WHERE " + where;
		this.sqlString = sql;
		return this;
	}
	/**
	 * 替换预SQL中的参数 获得可执行的SQL
	 * 
	 * @param preparedSql
	 *            预编译SQL(必须)
	 * @param args
	 *            注入sql中的对象集 (可选) 或者一个Map
	 * @return 可执行的SQL
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Sql replaceParameter(String preparedSql, Object... args) {
		Map<String, Object> map = new HashMap<>();

		if (args.length == 1 && args[0].getClass().getSuperclass().getName().equals("java.util.AbstractMap")) {
			map = (Map<String, Object>) args[0];
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = "${" + entry.getKey() + "}";
				Object value = entry.getValue();
				preparedSql = preparedSql.replace(key, "'" + value.toString() + "'");
			}
		} else {

			for (Object obj : args) {
				Class<?> cls = obj.getClass();
				Field[] field = cls.getDeclaredFields();
				for (Field f : field) {
					f.setAccessible(true);
					try {
						if (f.get(obj) != null) {
							map.put("${" + f.getName() + "}", "'" + f.get(obj).toString() + "'");
						} else {
							map.put("${" + f.getName() + "}", "null");
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				preparedSql = preparedSql.replace(key, value.toString());
			}
		}
		this.sqlString = preparedSql;
		return this;
	}

}

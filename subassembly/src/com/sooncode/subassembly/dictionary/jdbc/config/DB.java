package com.sooncode.subassembly.dictionary.jdbc.config;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * 数据字典 数据库配置类
 * 
 * @author hechen
 *
 */
public class DB {
	
	public final static Logger logger = Logger.getLogger("DB.class");
	/** 数据库驱动类名称 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/** 数据库IP地址 */
	private static final String IP = "localhost";

	/** 数据库端口 */
	private static final String PORT = "3306";

	/** 数据库名称 */
	private static final String DATA_NAME = "test";

	/** 数据库字符编码 */
	private static final String ENCODEING = "utf8";

	/** 数据库用户名 */
	private static final String USERNAME = "root";

	/** 数据库密码 */
	private static final String PASSWORD = "hechenwe@gmail.com";

	
	/**
     * 获取数据库连接
     * @return
     */
	public static Connection getConnection() {

		String mysqlUrl = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATA_NAME + "?useUnicode=true&characterEncoding=" + ENCODEING;
		Connection connnection = null;
		try {
			Class.forName(DRIVER);
			connnection = DriverManager.getConnection(mysqlUrl, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connnection;
	}

	/**
	 * 关闭连接资源
	 * @param objs 含有colse()方法的对象集合
	 */
	public static void close(Object... objs) {
		for (Object obj : objs) {
			try {
				Method method = obj.getClass().getMethod("close");
				if (method != null) {
					method.invoke(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

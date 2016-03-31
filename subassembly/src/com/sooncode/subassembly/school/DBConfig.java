package com.sooncode.subassembly.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 全国地区服务组件 数据库配置
 * 
 * @author hechen
 *
 */
public class DBConfig {

	/** 数据库驱动类名称 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/** 数据库IP地址 */
	private static final String IP = "localhost";

	/** 数据库端口 */
	private static final String PORT = "3306";

	/** 数据库名称 */
	private static final String DATA_NAME = "good_student";

	/** 数据库字符编码 */
	private static final String ENCODEING = "utf8";

	/** 数据库用户名 */
	private static final String USERNAME = "root";

	/** 数据库密码 */
	private static final String PASSWORD = "root";

	public static Connection getConnection() {

		String mysqlUrl = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATA_NAME + "?useUnicode=true&characterEncoding=" + ENCODEING;
		Connection connnection = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connnection = DriverManager.getConnection(mysqlUrl, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connnection;
	}

	/**
	 * 关闭连接资源
	 * 
	 * @param resultSet
	 * @param statement
	 * @param conn
	 */
	public static void closeAll(ResultSet resultSet, Statement statement, Connection conn) {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

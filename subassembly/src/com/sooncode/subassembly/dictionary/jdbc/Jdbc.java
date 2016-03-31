package com.sooncode.subassembly.dictionary.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.sooncode.subassembly.dictionary.jdbc.config.DB;
 

/**
 * JDBC 执行SQL语句
 * 
 * @author pc
 *
 */
public class Jdbc {

	public final static Logger logger = Logger.getLogger("JdbcUtil.class");
	private static int counter = 0;  
	/**
	 * 执行非查询语句
	 * 
	 * @param connection
	 *            数据库连接
	 * @param sql
	 *            可执行的非查询语句
	 * @return 一般情况是返回受影响的行数,当有主键为自增字段,在添加数据时返回 自增值
	 */
	public static Long executeUpdate(Connection connection, String sql) {
		if (connection == null) {
			return 0L;
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long n = 0L;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			n = (long) preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys(); // 获取ID
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				return id;
			} else {
				return n;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		} finally {
			// 关闭resultSet资源
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 关闭preparedStatement资源
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 关闭connection资源
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 执行查询语句
	 * 
	 * @param sql可执行SQL
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> executeQuery(Connection connection, String sql) {

		List<Map<String, Object>> resultList = new ArrayList<>();
		connection = DB.getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<>();

				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = resultSetMetaData.getColumnLabel(i).toUpperCase();// 获取别名
					Object columnValue = resultSet.getObject(i);
					map.put(columnName, columnValue);
				}
				resultList.add(map);
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DB.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 执行存储过程
	 * @param connection 数据源
	 * @param sql 存储过程 调用SQL语句 ,其中约定最后一个参数为输出参数(可以没有输出参数,输入参数). 
	 * @param in 存储过程需要的输入参数集
	 * @return 存储过程的 返回参数值,当没有返回参数时 返回null
	 */
	public static Object executeProcedure(Connection connection, String sql, Object... in) {
        //sql 中参数的个数  
	     int n = countParameter(sql,"?");
		// 创建调用存储过程的预定义SQL语句

		CallableStatement callableStatement = null;
		try {
			// 创建过程执行器
			callableStatement = connection.prepareCall(sql);
			// 设置入参和出参
			for (int i = 1; i <= in.length; i++) {
				callableStatement.setObject(i, in[i-1]);
			}
			if(n - in.length == 1){
				callableStatement.registerOutParameter(n, Types.JAVA_OBJECT); // 注册出参
				callableStatement.executeUpdate();
				Object result = callableStatement.getObject(n);
				return result;
			}else{
				callableStatement.executeUpdate();
				return null;
			}
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {

			try {
				callableStatement.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 计算sql中的参数个数
	 * @param sql 
	 * @param parameter "?"
	 * @return 参数个数
	 */
	private static int countParameter(String sql,String parameter) {
		 
        if (sql.indexOf(parameter) == -1) {  
            return 0;  
        } else if (sql.indexOf(parameter) != -1) {  
            counter++;  
            countParameter(sql.substring(sql.indexOf(parameter) +  
            		parameter.length()), parameter);  
               return counter;  
        }  
            return 0;  
    }  
	
	
	public static void main(String[] args) {
		String sql = "{call proc_name2(?,?)}";
		Object o = executeProcedure(DB.getConnection(), sql,2);
		logger.info(o);
	}
}

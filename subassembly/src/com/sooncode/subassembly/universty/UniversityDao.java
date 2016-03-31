package com.sooncode.subassembly.universty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

 

public class UniversityDao {

	private  static Connection connection;
	private  static Statement statement;

	/**学院表*/
	private static final String COLLEGE_TABEL = "SUBASSEMBLY_UNIVERSITY_COLLEGE";
	
	/**省市表*/
	private static final String PROVINCE_TABEL = "SUBASSEMBLY_UNIVERSITY_PROVINCES";  
	                                                                                 
	/**高校表*/
	private static final String UNIVERSITY_TABEL = "SUBASSEMBLY_UNIVERSITY_UNIVERSITY";


	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	/**
	 * 高校所在省市
	 * 
	 * @return 高校所在省市的 List集合
	 * @throws SQLException
	 */
	public static List<Province> getProvince() throws SQLException {

		String sql = "SELECT PROVINCE_ID , PROVINCE FROM " + PROVINCE_TABEL ;

		return  getProvinceBySql(sql);

	}

	
	
	/**
	 * 获得省市的所有高校
	 * 
	 * @param provinceId 省市编号
	 * @return 高校集合
	 * @throws SQLException
	 */
	public static List<University> getUniversity(Integer provinceId) throws SQLException {

		String sql = "SELECT UNIVERSITY_ID ,UNIVERSITY , PROVINCE_ID ,TYPE FROM " + UNIVERSITY_TABEL + " WHERE PROVINCE_ID = " + provinceId;

		return  getUniversityBySql(sql);

	}
	/**
	 * 根据关键字查询高校
	 * 
	 * @param provinceId 省市编号
	 * @return 高校集合
	 * @throws SQLException
	 */
	public static List<University> getUniversityByKeyWord(String keyWord) throws SQLException {
		
		String sql = "SELECT UNIVERSITY_ID ,UNIVERSITY , PROVINCE_ID  ,TYPE  FROM " + UNIVERSITY_TABEL + " WHERE UNIVERSITY LIKE '%" + keyWord +"%' LIMIT 0,20";
		
		return  getUniversityBySql(sql);
		
	}
	/**
	 * 获得高校的所有学院
	 * 
	 * @param universityId 高校编号
	 * @return 学院集合
	 * @throws SQLException
	 */
	public static List<College> getColleges(Integer universityId) throws SQLException {
		
		String sql = "SELECT COLLEGE_ID , COLLEGE , UNIVERSITY_ID    FROM " + COLLEGE_TABEL + " WHERE UNIVERSITY_ID = " + universityId;
		
		return  getCollegeBySql(sql);
		
	}

	
	 
	 

	/**
	 * 根据SQL查询高校所在省市信息
	 * 
	 * @param sql 查询语句
	 * @return 省市信息集合
	 * @throws SQLException
	 */
	private static List<Province>  getProvinceBySql(String sql) throws SQLException {
		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<Province> provinces = new ArrayList<Province>();
		while (resultSet.next()) {

			Integer provinceId = resultSet.getInt("PROVINCE_ID");
			String  provinceName = resultSet.getString("PROVINCE");
 

			Province province = new Province();

			province.setProvinceId(provinceId);
			province.setProvince(provinceName);
			
			provinces.add(province);

		}

		DBConfig.closeAll(resultSet, statement, connection);
		return provinces;

	}
	/**
	 * 根据SQL查询高校信息
	 * 
	 * @param sql 查询语句
	 * @return 高校信息
	 * @throws SQLException
	 */
	private static List<University>  getUniversityBySql(String sql) throws SQLException {
		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		List<University> universities = new ArrayList<University>();
		while (resultSet.next()) {
			
			Integer universityId = resultSet.getInt("UNIVERSITY_ID");
			String  universityName = resultSet.getString("UNIVERSITY");
			                                            //university
			Integer  provinceId = resultSet.getInt("PROVINCE_ID");
			
			String type = resultSet.getString("TYPE");
			
			University university = new University();
			
			university.setUniversityId(universityId);
			university.setUniversity(universityName);
			university.setProvinceId(provinceId);
			university.setType(type);
			universities.add(university);
			
		}
		
		DBConfig.closeAll(resultSet, statement, connection);
		return universities;
		
	}
	/**
	 * 根据SQL查询高校的学院信息
	 * 
	 * @param sql 查询语句
	 * @return 学院信息
	 * @throws SQLException
	 */
	private static List<College>  getCollegeBySql(String sql) throws SQLException {
		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		List<College> colleges = new ArrayList<College>();
		while (resultSet.next()) {
			
			Integer collegeId = resultSet.getInt("COLLEGE_ID");
			String  collegeName = resultSet.getString("COLLEGE");
			 
			Integer  universityId = resultSet.getInt("UNIVERSITY_ID");
			
			
			College college = new College();
			college.setCollegeId(collegeId);
			college.setCollege(collegeName);
			college.setUniversityId(universityId);
		 
			
			colleges.add(college);
			
		}
		
		DBConfig.closeAll(resultSet, statement, connection);
		return colleges;
		
	}

	public static void main(String[] args) throws SQLException {

		//System.out.println("AreaService.main()" + getProvince() );
		//System.out.println("AreaService.main()" + getUniversity(1) );
		//System.out.println("AreaService.main()" + getUniversityByKeyWord("大学") );
		
		
		
	}

}

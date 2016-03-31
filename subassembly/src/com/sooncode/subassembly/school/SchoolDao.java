package com.sooncode.subassembly.school;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchoolDao {

	private static Connection connection;
	private static Statement statement;

	/** 表名 */
	private static final String TABLE_NAME = "SUBASSEMBLY_SCHOOL";

	/** 字段集 */
	private static final String COLUMN_LIST = "SCHOOL_ID ,SCHOOL_NAME , SCHOOL_ADDRESS ,SCHOOL_PHONE ,SCHOOL_POSTALCODE ,SCHOOL_WEBSITE ,SCHOOL_XUEDUAN ,SCHOOL_PROVINCE ,SCHOOL_QU , SCHOOL_XIAN ,PAGE ";

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 获取全国的省,直辖市
	 * 
	 * @return 全国的省,直辖市 List 集合
	 * @param xueduan
	 *            学段
	 * @throws SQLException
	 */
	public static List<String> getProvince(String xueduan) throws SQLException {

		String sql = "SELECT DISTINCT SCHOOL_PROVINCE  FROM " + TABLE_NAME + " WHERE SCHOOL_XUEDUAN = " + "'" + xueduan + "'";
		// System.out.println("---SQL---"+sql);

		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<String> provinces = new ArrayList<String>();
		while (resultSet.next()) {
			String schoolProvince = resultSet.getString("SCHOOL_PROVINCE");
			provinces.add(schoolProvince);
		}

		DBConfig.closeAll(resultSet, statement, connection);
		return provinces;

	}

	/**
	 * 
	 * 获取全国的省,直辖市 的区, 市
	 * 
	 * @return 全国的省,直辖市 的区 市 集合
	 * @param xueduan
	 *            学段
	 * @param province
	 *            省,直辖市
	 * @throws SQLException
	 */
	public static List<String> getSchoolRegion(String province, String xueduan) throws SQLException {

		String sql = "SELECT DISTINCT SCHOOL_QU  FROM " + TABLE_NAME + " WHERE  SCHOOL_PROVINCE = '" + province + "' AND SCHOOL_QU IS NOT NULL  AND SCHOOL_XUEDUAN = " + "'" + xueduan + "'";
		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<String> regions = new ArrayList<String>();
		while (resultSet.next()) {
			String schoolQu = resultSet.getString("SCHOOL_QU");
			regions.add(schoolQu);
		}

		return regions;

	}

	/**
	 * 
	 * @param province
	 *            省和直辖市名称
	 * @return 学校 list 集合
	 * @throws SQLException
	 */
	public static List<School> getSchoolByRegion(String region, String xueduan) throws SQLException {

		String sql = "SELECT " + COLUMN_LIST + "  FROM " + TABLE_NAME + " WHERE SCHOOL_QU = '" + region + "' AND SCHOOL_XUEDUAN ='" + xueduan + "'";
		// System.out.println("SchoolDao.getSchoolByProvince()" + sql);
		return getSchoolBySql(sql);

	}

	/**
	 * 根据SQL查询学校信息
	 * 
	 * @param sql
	 *            查询语句
	 * @return 学校信息
	 * @throws SQLException
	 */
	private static List<School> getSchoolBySql(String sql) throws SQLException {

		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<School> schools = new ArrayList<School>();
		while (resultSet.next()) {

			Long schoolId = resultSet.getLong("SCHOOL_ID");
			String schoolName = resultSet.getString("SCHOOL_NAME");
			String schoolAddress = resultSet.getString("SCHOOL_ADDRESS");

			String schoolPhone = resultSet.getString("SCHOOL_PHONE");
			String schoolPostalcode = resultSet.getString("SCHOOL_POSTALCODE");
			String schoolWebsite = resultSet.getString("SCHOOL_WEBSITE");

			String schoolXueduan = resultSet.getString("SCHOOL_XUEDUAN");
			String schoolProvince = resultSet.getString("SCHOOL_PROVINCE");
			String schoolQu = resultSet.getString("SCHOOL_QU");

			String schoolXian = resultSet.getString("SCHOOL_XIAN");
			int page = resultSet.getInt("PAGE");

			School school = new School();
			school.setSchoolId(schoolId);
			school.setSchoolName(schoolName);
			school.setSchoolAddress(schoolAddress);

			school.setSchoolPhone(schoolPhone);
			school.setSchoolProvince(schoolProvince);
			school.setSchoolWebsite(schoolWebsite);

			school.setSchoolXueduan(schoolXueduan);
			school.setSchoolPostalcode(schoolPostalcode);
			school.setSchoolQu(schoolQu);

			school.setSchoolXian(schoolXian);
			school.setPage(page);

			schools.add(school);

		}
        
		DBConfig.closeAll(resultSet, statement, connection);
		return schools;

	}

	//
	// public static String jqAdress (Long schoolId) throws SQLException{
	//
	// String sql = "SELECT RIGHT (LEFT(t.school_address, 6), 3) as XXOO FROM
	// subassembly_school t WHERE t.school_id = " + schoolId ;
	// // connection = DBConfig.getConnection();
	// statement = connection.createStatement();
	// ResultSet resultSet = statement.executeQuery(sql);
	// String xxoo = "";
	// while (resultSet.next()) {
	// xxoo = resultSet.getString("XXOO");
	// }
	// //DBConfig.closeAll(resultSet, statement, connection);
	// return xxoo;
	// }

	// public static List<School> selectSchool(String likeStrng ) throws
	// SQLException{
	//
	// String sql = "SELECT "+ COLUMN_LIST +" FROM " + TABLE_NAME + " WHERE
	// SCHOOL_ADDRESS LIKE " + likeStrng;
	// // System.out.println("SchoolDao.getSchoolByProvince()" + sql);
	// return getSchoolBySql(sql);
	//
	// }

	// public static int updateSchool (String qu ,String address) throws
	// SQLException{
	//
	// String sql = "UPDATE " + TABLE_NAME + " SET school_qu = ? WHERE
	// school_address like ? ";
	// // connection = DBConfig.getConnection();
	// statement = connection.createStatement();
	// PreparedStatement preparedStatement = null;
	// preparedStatement = connection.prepareStatement(sql);
	// preparedStatement.setString(1, qu);
	// preparedStatement.setString(2, address+"%");
	// return preparedStatement.executeUpdate();
	//
	// }
	// public static int updateSchoolByXxooAndId(String qu ,Long schoolId)
	// throws SQLException{
	//
	// String sql = "UPDATE " + TABLE_NAME + " SET school_qu = ? WHERE school_id
	// = ? ";
	// //connection = DBConfig.getConnection();
	// statement = connection.createStatement();
	// PreparedStatement preparedStatement = null;
	// preparedStatement = connection.prepareStatement(sql);
	// preparedStatement.setString(1, qu);
	// preparedStatement.setLong(2, schoolId);
	// int n = preparedStatement.executeUpdate();
	// //DBConfig.closeAll(null, statement, connection);
	// return n;
	//
	// }

	// public static void main(String[] args) throws SQLException {
	//
	// //System.out.println("AreaService.main()" +
	// getProvince(SchoolConstant.HIGH_SCHOOL));
	// //System.out.println("AreaService.main()" + updateSchool("顺义"
	// ,"北京市顺义区"));
	// // System.out.println("AreaService.main()" + getSchoolByProvince("北京"
	// ,"高中"));
	//// List<School> lists = selectSchool("'__省___市%'");
	//// for (School school : lists) {
	//// String str = jqAdress(school.getSchoolId());
	//// updateSchoolByXxooAndId(str,school.getSchoolId());
	//// }
	//
	// //System.out.println("SchoolDao.main()" +jqAdress(4689L));
	//
	// List<String> strings = getProvince(SchoolConstant.HIGH_SCHOOL);
	// for (String string : strings) {
	//
	// System.out.println("SchoolDao.main()" +getSchoolRegion(string,"高中"));
	// List<String> strings2 = getSchoolRegion(string,"高中");
	// for (String str : strings2) {
	//
	// System.out.println( getSchoolByRegion(str,"高中"));
	//
	// }
	// }
	//
	// }

}

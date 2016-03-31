package com.sooncode.subassembly.area;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {

	private  static Connection connection;
	private  static Statement statement;

	/** 表名 */
	private static final String TABLE_NAME = "SUBASSEMBLY_AREA";

	/** 第一级 地域 */
	private static final String ONE_RANK = "1";

	/** 字段集 */
	private static final String COLUMN_LIST = "AREA_CODE , PARENT_CODE , AREA_NAME , AREA_RANK , IS_LAST ,AREA_TYPE , AREA_ABBREVIATION , PHONE_CODE,POSTAL_CODE ,ALL_NAME";

	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 
	/**
	 * 获取1级地区信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<Area> getOneRankArea() throws SQLException {

		String sql = "SELECT " + COLUMN_LIST + " FROM " + TABLE_NAME + " WHERE AREA_RANK = " + ONE_RANK;

		return  getAreaBySql(sql);

	}

	
	
	/**
	 * 获得下一级地区信息
	 * 
	 * @param parentCode
	 * @return
	 * @throws SQLException
	 */
	public static List<Area> getNextArea(String parentCode) throws SQLException {

		String sql = "SELECT  " + COLUMN_LIST + " FROM " + TABLE_NAME + " WHERE PARENT_CODE = " + parentCode;

		return  getAreaBySql(sql);

	}

	
	
	/**
	 * 获取直辖市
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<Area> getMunicipality() throws SQLException {

		String sql = "SELECT  " + COLUMN_LIST + " FROM " + TABLE_NAME + " WHERE AREA_RANK = " + ONE_RANK + " AND AREA_NAME LIKE '%市'";

		return  getAreaBySql(sql);
	}

	
	
	/**
	 * 获取直辖市和省会
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<Area> getMunicipalityAndCapital() {

		String sql = "SELECT  " + COLUMN_LIST + " FROM " + TABLE_NAME + " WHERE AREA_RANK = " + ONE_RANK + " AND AREA_NAME LIKE '%市'";

		try {
			return getAreaBySql(sql);
		} catch (SQLException e) {
			return null;
		}
	}

	 

	/**
	 * 根据SQL查询地区信息
	 * 
	 * @param sql 查询语句
	 * @return 地区集合
	 * @throws SQLException
	 */
	private static List<Area>  getAreaBySql(String sql) throws SQLException {
		connection = DBConfig.getConnection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<Area> areas = new ArrayList<Area>();
		while (resultSet.next()) {

			String areaCode = resultSet.getString("AREA_CODE");
			String parentCode = resultSet.getString("PARENT_CODE");
			String areaName = resultSet.getString("AREA_NAME");

			String areaRank = resultSet.getString("AREA_RANK");
			String isLast = resultSet.getString("IS_LAST");
			String areaType = resultSet.getString("AREA_TYPE");

			String areaAbbreviation = resultSet.getString("AREA_ABBREVIATION");
			String phoneCode = resultSet.getString("PHONE_CODE");
			String postalCode = resultSet.getString("POSTAL_CODE");

			String allName = resultSet.getString("ALL_NAME");

			Area area = new Area();

			area.setAreaCode(areaCode);
			area.setAreaName(areaName);
			area.setAreaRank(areaRank);

			area.setIsLast(isLast);
			area.setParentCode(parentCode);
			area.setAreaAbbreviation(areaAbbreviation);

			area.setAreaType(areaType);
			area.setAllName(allName);
			area.setPhoneCode(phoneCode);

			area.setPostalCode(postalCode);
			areas.add(area);

		}

		DBConfig.closeAll(resultSet, statement, connection);
		return areas;

	}

	public static void main(String[] args) throws SQLException {

		System.out.println("AreaService.main()" + getOneRankArea() );
	}

}

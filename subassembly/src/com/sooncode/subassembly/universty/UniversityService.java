package com.sooncode.subassembly.universty;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

 

@Service
public class UniversityService {
	 
	 
	/**
	 * 高校所在省市
	 * 
	 * @return 高校所在省市的 List集合
	 * @throws SQLException
	 */
	public   List<Province> getProvince() {


		try {
			return  UniversityDao.getProvince();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	
	
	/**
	 * 获得省市的所有高校
	 * 
	 * @param provinceId 省市编号
	 * @return 高校集合
	 * @throws SQLException
	 */
	public   List<University> getUniversity(Integer provinceId)   {


		try {
			return  UniversityDao.getUniversity(provinceId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 获得高校的所有学院
	 * 
	 * @param universityId 高校编号
	 * @return 学院集合
	 * @throws SQLException
	 */
	public   List<College> getColleges(Integer universityId) throws SQLException {
		
		
		return UniversityDao.getColleges(universityId) ;
		
	}

	 public static void main(String[] args) {
		 
	}
	 /**
	  * 根据关键字查询高校
	  * 
	  * @param keyWord 关键字
	  * @return 学院集合
	  * @throws SQLException
	  */
	 public   List<University> getUniversityByKeyWord(String keyWord) throws SQLException {
		 
		 return UniversityDao.getUniversityByKeyWord(keyWord) ;
		 
	 }
	 
	 

}

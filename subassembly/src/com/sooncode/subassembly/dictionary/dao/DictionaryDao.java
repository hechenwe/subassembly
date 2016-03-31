package com.sooncode.subassembly.dictionary.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.entity.Dictionary;
import com.sooncode.subassembly.dictionary.jdbc.Dao;
import com.sooncode.subassembly.dictionary.jdbc.Jdbc;
import com.sooncode.subassembly.dictionary.jdbc.Sql;
import com.sooncode.subassembly.dictionary.jdbc.config.DB;
 
 
 

/**
 * 数据字典Dao
 * 
 * @author pc
 *
 */
public class DictionaryDao extends Dao<Dictionary>{
	 
	 
	public final static Logger logger = Logger.getLogger("DictionaryDao.class");

	/**
	 * 查询组内的所用字典数据
	 * 
	 * @param groupCode
	 * @return
	 */
	public  List<Dictionary> selectGroupDictionaries(Integer groupId) {
		
		Dictionary d = new Dictionary();
		d.setGroupId(groupId);
		d.setDictionaryState(1);
		
		String sql =  new Sql().select(d).toString();  
		//logger.info(sql); 
		
		Connection con = DB.getConnection();
		List<Map<String,Object>> list = Jdbc.executeQuery(con,sql);
		List<Dictionary> dictionaries =  super.getObjectList(list);
		 
		return dictionaries	;	
       
	}

	 
}

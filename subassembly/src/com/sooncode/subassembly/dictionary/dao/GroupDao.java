package com.sooncode.subassembly.dictionary.dao;

import java.util.List;
import org.apache.log4j.Logger;
import com.sooncode.subassembly.dictionary.entity.Groups;
import com.sooncode.subassembly.dictionary.jdbc.Dao;
import com.sooncode.subassembly.dictionary.jdbc.Jdbc;
import com.sooncode.subassembly.dictionary.jdbc.config.DB;
 
 

/**
 * 数据字典Dao
 * 
 * @author pc
 *
 */
public class GroupDao extends Dao<Groups> {

    public final static Logger logger = Logger.getLogger("GroupDao.class");
    
	/**
	 * 删除 一个组(假删除)
	 * @param group
	 * @return
	 */
	public Long deleteGroup(String groupCode) {
		String sql = "UPDATE " + "GROUPS" + "  SET GROUP_STATE = 0 WHERE GROUP_CODE = '" + groupCode+"'";
		
		return Jdbc.executeUpdate(DB.getConnection(), sql);
	}
	
	
    /**
     * 查询所有的分组
     * @return
     */
	public  List<Groups> selectAllGroups(){
		
	    Groups g = new Groups();
	    g.setGroupState(1);
	    
		return super.gets(g);
		
		
	}
	
	 

	 

}

package com.sooncode.subassembly.dictionary.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.dao.GroupDao;
import com.sooncode.subassembly.dictionary.entity.Groups;
import com.sooncode.subassembly.other.ParameterUtil;

/**
 * 数据字典服务类
 * 
 * @author pc
 *
 */
public class GroupService {
	
	public final static Logger logger = Logger.getLogger("GroupDao.class");

	private GroupDao groupDao = new GroupDao();
	
	
	/**
	 * 保存分组
	 * @param groups
	 * @return
	 */
	public Long saveGroups(Groups groups){
		if( ParameterUtil.isObjectNull(groups)==false){
			return 0L;
		}
		Long n = groupDao.save(groups);
		return n ;
	}
	/**
	 * 修改分组
	 * @param groups 其中 groupId 不能为空
	 * @return 
	 */
	public int updateGroups(Groups groups){
		if( groups == null || groups.getGroupId()==null){
			return 0;
		}
		int n = groupDao.update(groups) ;
		return n ;
	}
	
	
	/**
	 * 删除分组
	 * @param groupCode
	 * @return
	 */
	public Long deleteGroups(String groupCode){
		if(groupCode == null){
			return 0L;
		}
		long n = groupDao.deleteGroup(groupCode);
		return n;
		
	}
	
	
	
	public static void main(String[] args) {
		
		Groups g = new Groups();
		g.setGroupId(234);
		g.setGroupCode("thisType");
		g.setGroupName("当前类型");
		g.setGroupState(1);
		g.setCreateDate(new Date());
		
		GroupService gs = new GroupService();
		gs.saveGroups(g);
	}
}

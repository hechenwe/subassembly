package com.sooncode.subassembly.dictionary.dao;

import java.util.List;

import com.sooncode.subassembly.dictionary.entity.Dictionary;
import com.sooncode.subassembly.dictionary.entity.Groups;

/**
 * 数据字典Dao接口
 * 
 * @author pc
 *
 */
public interface DictionaryDaoI  {
	 

	/**
	 * 保存数据字典分组
	 * @param groups 数据字典分组
	 * @return
	 */
	public Long addDictionaryGroups(Groups groups) ;
		 
	/**
	 * 修改数据字典分组
	 * @param groups 其中 groupId 不能为空
	 * @return 
	 */
	public Long updateDictionaryGroups(Groups groups);
		 
	
	
	/**
	 * 删除数据字典分组
	 * @param groupCode 数据字典分组代码
	 * @return
	 */
	public Long removeDictionaryGroups(String groupCode); 
	
	/**
	 * 添加数据字典数据项
	 * @param dictionary
	 * @return
	 */
	public Long addDictionary(Dictionary dictionary);
	
	/**
	 * 更新数据字典数据项
	 * @param dictionary
	 * @return
	 */
	public Long updateDictionary(Dictionary dictionary);
	
	/**
	 * 删除数据字典数据项
	 * @param dictionary
	 * @return
	 */
	public Long removeDictionary(Integer dictionaryId);
	 

	/**
	 * 获取数据字典组内是所用数据项
	 * 
	 * @param groupCode 组代码
	 *            
	 * @return 字典信息集合
	 */
	public  List<Dictionary> getDictionaries(String groupCode);
		 

	/**
	 * 获取数据字典
	 * 
	 * @param groupCode 组代码
	 *            
	 * @param dictionaryCode 字典代码
	 *            
	 * @return 字典信息
	 */
	public  Dictionary getDictionary(String groupCode, String dictionaryCode);  	 
	 
}

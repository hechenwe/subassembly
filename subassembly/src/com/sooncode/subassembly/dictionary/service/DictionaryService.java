package com.sooncode.subassembly.dictionary.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.sooncode.subassembly.dictionary.entity.Dictionary;
import com.sooncode.subassembly.dictionary.entity.Groups;


/**
 * 数据字典服务类
 * 
 * @author pc
 *
 */
public class DictionaryService implements DictionaryServiceI {
	
	public final static Logger logger = Logger.getLogger("GroupDao.class");

	@Override
	public Boolean addDictionaryGroups(Groups groups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateDictionaryGroups(Groups groups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeDictionaryGroups(String groupCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeDictionary(Integer dictionaryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dictionary> getDictionaries(String groupCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dictionary getDictionary(String groupCode, String dictionaryCode) {
		// TODO Auto-generated method stub
		return null;
	}

	 
}

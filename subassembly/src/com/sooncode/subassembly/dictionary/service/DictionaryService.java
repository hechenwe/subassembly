package com.sooncode.subassembly.dictionary.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.entity.Dictionary;
import com.sooncode.subassembly.dictionary.util.Cache;

/**
 * 数据字典服务类
 * 
 * @author pc
 *
 */
public class DictionaryService {
	
	public final static Logger logger = Logger.getLogger("GroupDao.class");

	/**
	 * 获取组内是所用数据项
	 * 
	 * @param groupCode
	 *            组代码
	 * @return 字典信息集合
	 */
	public static List<Dictionary> selectGroupDictionaries(String groupCode) {
		Map<String, List<Dictionary>> dictionariesCache = Cache.dictionariesCache;
		List<Dictionary> dictionaries = dictionariesCache.get(groupCode);
		if (dictionaries == null) { // 内存里没有缓存
			Cache.updateDictioanaryCache(groupCode);
			dictionaries = dictionariesCache.get(groupCode);//再获取一次
		} else {
			Cache.updateDictionaryCacheMess(groupCode);
		}

		return dictionaries;

	}

	/**
	 * 获取数据字典
	 * 
	 * @param groupCode
	 *            组代码
	 * @param dictionaryCode
	 *            字典代码
	 * @return 字典信息
	 */
	public static Dictionary selectDictionary(String groupCode, String dictionaryCode) {
		Map<String, List<Dictionary>> dictionariesCache = Cache.dictionariesCache;
		List<Dictionary> dictionaries = dictionariesCache.get(groupCode);
		if (dictionaries == null) { // 内存里没有缓存
			Cache.updateDictioanaryCache(groupCode);
			dictionaries = dictionariesCache.get(groupCode);//再获取一次
		} else {
			Cache.updateDictionaryCacheMess(groupCode);
		}
		Dictionary dictionary = null;
		for (Dictionary d : dictionaries) {
			if (d.getDictionaryCode().equals(dictionaryCode)) {
				dictionary = d;
				break;
			}
		}
		return dictionary;
	}

	public static void main(String[] args) {
		 logger.info(selectGroupDictionaries("userType"));
	     logger.info(selectDictionary("userType","1"));

	}
}

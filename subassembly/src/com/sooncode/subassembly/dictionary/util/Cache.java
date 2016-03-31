package com.sooncode.subassembly.dictionary.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.dao.DictionaryDao;
import com.sooncode.subassembly.dictionary.dao.GroupDao;
import com.sooncode.subassembly.dictionary.entity.Dictionary;
import com.sooncode.subassembly.dictionary.entity.Groups;

/**
 * 数据字典缓存
 * 
 * @author pc
 *
 */
public class Cache {

	public final static Logger logger = Logger.getLogger("Cache.class");

	public static Map<String, DictionaryCacheMess> dictCacheMessMap = new HashMap<String, DictionaryCacheMess>();

	/**
	 * 组缓存
	 */
	public static Map<String, Groups> groupsCache;

	/**
	 * 数据字典缓存
	 */
	public static Map<String, List<Dictionary>> dictionariesCache = new HashMap<String, List<Dictionary>>();;

	public static final String GROUP_FILE_ALL_NAME = "groupsCache.dat";

	public static final String DICTIONARY_FILE_ALL_NAME = "dictionaryCache.dat";

	/**
	 * 初始化
	 */
	static {
		saveGroups2Disk();
		refreshGroupsCache();
	}

	/**
	 * 将组信息从数据库保存到磁盘
	 */
	public static void saveGroups2Disk() {

		List<Groups> groups = new GroupDao().selectAllGroups();
		Map<String, Groups> groupsMap = new HashMap<>();
		for (Groups group : groups) {
			groupsMap.put(group.getGroupCode(), group);
		}
		SaveObjectUtil.saveObject(GROUP_FILE_ALL_NAME, groupsMap);

	}

	/**
	 * 将数据字典从数据库保存到磁盘
	 */
	public static void saveDictionary2Disk() {
		List<Groups> groups =  new GroupDao().selectAllGroups();
		Map<String, List<Dictionary>> dictionaryMap = new HashMap<>();

		for (Groups group : groups) {
			List<Dictionary> dictionaries =new DictionaryDao().selectGroupDictionaries(group.getGroupId());
			dictionaryMap.put(group.getGroupCode(), dictionaries);
		}

		SaveObjectUtil.saveObject(DICTIONARY_FILE_ALL_NAME, dictionaryMap);

	}

	/**
	 * 刷新组缓存
	 */
	@SuppressWarnings("unchecked")
	public static void refreshGroupsCache() {
		groupsCache = (Map<String, Groups>) SaveObjectUtil.readObject(GROUP_FILE_ALL_NAME);
	}

	/**
	 * 更新字典缓存
	 * 
	 * @param groupCode
	 */
	public static void updateDictioanaryCache(String groupCode) {

		List<Dictionary> dictionaries = null;

		Map<String, Groups> groupMap = Cache.groupsCache;
		Groups g = groupMap.get(groupCode);
		if(g == null){
			return;
		}
		dictionaries = new DictionaryDao().selectGroupDictionaries(g.getGroupId());

		Cache.dictionariesCache.put(groupCode, dictionaries);// 更新到缓存中
		DictionaryCacheMess d = new DictionaryCacheMess();
		d.setGroupCode(groupCode);
		d.setLastVisitDate(new Date());
		d.setTimes(1);
		Cache.dictCacheMessMap.put(groupCode, d);
	}

	/**
	 * 更新字典访问统计数据
	 * 
	 * @param groupCode
	 */
	public static void updateDictionaryCacheMess(String groupCode) {

		DictionaryCacheMess d = (DictionaryCacheMess) Cache.dictCacheMessMap.get(groupCode);
		if (d == null) {
			d = new DictionaryCacheMess();
			d.setTimes(0);
		}
		d.setGroupCode(groupCode);
		d.setLastVisitDate(new Date());
		d.setTimes(d.getTimes() + 1);
		Cache.dictCacheMessMap.put(groupCode, d);
	}

	public static void main(String[] args) {
		 
	}
}

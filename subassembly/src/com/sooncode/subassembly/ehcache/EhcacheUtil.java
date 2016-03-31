package com.sooncode.subassembly.ehcache;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ehcache 缓存工具类
 * 
 * @author hechen
 *
 */
public class EhcacheUtil {

	/**
	 * 创建缓存容器管理器
	 * 
	 * @param ehcachCinfigPath
	 *            配置文件, 如:"config/ehcache.xml"
	 * @return
	 */
	public static CacheManager creatCacheManager(String ehcachCinfigPath) {
		return new CacheManager(ehcachCinfigPath);
	}

	/**
	 * 获取所有缓存容器名称
	 * 
	 * @param cacheManager
	 * @return
	 */
	public static List<String> getCacheNames(CacheManager cacheManager) {
		if (cacheManager == null) {
			return null;
		}
		String[] cacheNames = cacheManager.getCacheNames();
		List<String> strings = new ArrayList<>();
		for (String string : cacheNames) {
			strings.add(string);
		}
		return strings;
	}

	/**
	 * 向管理器中添加缓存容器
	 * 
	 * @param cacheManager
	 * @param cachaName
	 */
	public static void addCache(CacheManager cacheManager, String cachaName) {
		cacheManager.addCache(cachaName);
	}

	/**
	 * 获取管理器中的缓存容器
	 * 
	 * @param cacheManager
	 *            管理器
	 * @param cachaName
	 *            缓存容器名称
	 * @return 缓存容器
	 */
	public static Cache getCache(CacheManager cacheManager, String cachaName) {
		return cacheManager.getCache(cachaName);
	}
    
	/**
	 * 从管理器中移除缓存容器
	 * @param cacheManager
	 * @param cachaName
	 */
	public static void removeCache(CacheManager cacheManager,String cachaName){
		cacheManager.removeCache(cachaName);
	}
	/**
	 * 关闭缓存管理器
	 * @param cacheManager
	 */
	public static void shutdowdManager(CacheManager cacheManager){
		cacheManager.shutdown();
	}
	
	
	/**
	 * 创建缓存
	 * @param name 缓存容器名称
	 * @param maxElementsInMemory 内存中创建对象的最大值
	 * @param overflowToDisk 设置当内存中缓存达到 maxInMemory 限制时元素是否可写到磁盘上
	 * @param eternal 设置元素是否永久驻留
	 * @param timeToLiveSeconds 设置某个元素消亡前的停顿时间。也就是在一个元素消亡之前，两次访问时间的最大时间间隔值。只能在元素不是永久驻留时有效。
	 * @param timeToIdleSeconds 设置某个元素消亡前的生存时间。也就是一个元素从构建到消亡的最大时间间隔值。只能在元素不是永久驻留时有效。
	 * @return
	 */
	public static Cache newCache(String name, int maxElementsInMemory, boolean overflowToDisk, boolean eternal, long timeToLiveSeconds, long timeToIdleSeconds) {
		return new Cache(name,maxElementsInMemory,overflowToDisk,eternal,timeToLiveSeconds,timeToIdleSeconds);
	}

	/**
	 * 获取Spring xml配置的bean
	 * 
	 * @param configPath
	 *            配置文件的路径和名称
	 * @param beanName
	 *            bean名称
	 * @return bean对象
	 */
	public static Object getSpringBean(String configPath, String beanName) {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(configPath);
		return ac.getBean(beanName);
	}

	/**
	 * 添加对象到缓存容器中
	 * 
	 * @param cache
	 *            缓存(容器)
	 * @param key
	 *            缓存对象的关键字
	 * @param value
	 *            缓存对象
	 */
	public static void putObject(Cache cache, String key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	/**
	 * 获取缓存中的对象
	 * 
	 * @param cache
	 *            缓存容器
	 * @param key
	 *            缓存对象关键字
	 * @return 缓存对象
	 */
	public static Object getObject(Cache cache, String key) {
		Element element = cache.get(key);
		if (element != null) {
			return element.getObjectValue();
		} else {
			return null;
		}
	}
    
	/**
	 * 从缓存容器中移除对象
	 * @param cache
	 * @param key
	 */
	public static void removeObject(Cache cache,String key){
		cache.remove(key);
	}
	
	
	public static void main(String[] args) {
		/*
		 * Cache ehCache = (Cache) getStringBean("applicationContext.xml",
		 * "userCache"); putObject(ehCache, "username", "hechen");
		 * 
		 * System.out.println(getObject(ehCache, "username"));
		 */

		CacheManager cm = creatCacheManager("config/ehcache.xml");
		cm.addCache("treeCache");
		List<String> cacheNames = getCacheNames(cm);
		for (String string : cacheNames) {
			System.out.println("EhcacheUtil.main()" + string);
		}
	}
}

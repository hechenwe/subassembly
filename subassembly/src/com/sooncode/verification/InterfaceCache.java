package com.sooncode.verification;

 
import java.util.HashMap;
import java.util.Map;

import com.sooncode.verification.enumes.Method;
import com.sooncode.verification.enumes.ParType;
import com.sooncode.verification.moduler.Arrays;
import com.sooncode.verification.moduler.Interface;
import com.sooncode.verification.moduler.Parameteres;

/**
 * 接口模型 缓存
 * @author pc
 *
 */

public class InterfaceCache {

	private static Map<String,Interface> cache = new HashMap<>();
	
	
	public static void putInterface(Interface interfac){
		cache.put(interfac.getUrl(),interfac);
	}
	public static void putInterface(String url,Method method,ParType parameterType,Parameteres parameters , Arrays arrays){
		Interface interfac = new Interface(url, method, parameterType, parameters, arrays);
		cache.put(interfac.getUrl(),interfac);
	}
	
	public static Interface getInterface(String key){
		return cache.get(key);
	}
	
	 
}

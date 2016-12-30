package com.sooncode.verification.service;


import java.util.Hashtable;
import java.util.Map;

import com.sooncode.verification.moduler.Controller;

/**
 * 加载参数配置文件
 * 
 * @author pc
 *
 */
public class ControllerParameterManager {
	public static Map<String, Controller> controllerMap = new Hashtable<>();

	public ControllerParameterManager(String paraXmls) {
		String[] strs = paraXmls.split(";");
		for (String p : strs) {
			new ControllerParameter(p);
		}
	}

}

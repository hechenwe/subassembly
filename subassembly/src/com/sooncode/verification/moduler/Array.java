package com.sooncode.verification.moduler;

import java.util.List;

/**
 * JSON格式 数组 模型
 * 
 * @author pc
 *
 */
public class Array {
	/** 数组 KEY */
	private String arrayKey;

	private List<Parameter> parameters;
 
	
	public Array(String arrayKey,Parameteres parameters){
		this.arrayKey = arrayKey;
		this.parameters = parameters.getPes();
	}
	
	
	public String getArrayKey() {
		return arrayKey;
	}

	public void setArrayKey(String arrayKey) {
		this.arrayKey = arrayKey;
	}


	public List<Parameter> getParameters() {
		return parameters;
	}


	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
 
	
}

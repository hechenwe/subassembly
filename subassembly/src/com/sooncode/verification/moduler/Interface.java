package com.sooncode.verification.moduler;

import java.util.List;

import com.sooncode.verification.enumes.Method;
import com.sooncode.verification.enumes.ParType;

 

/**
 * 接口 模型
 * 
 * @author pc
 *
 */
public class Interface {
	/** 接口地址 */
	private String url;

	/** 请求方式 */
	private String method;

	/** 参数类型 */
	private String parameterType;

	/**
	 * JSON参数中的数组模型
	 */
	private List<Array> arrays;

	/** 接口的参数 */
	private List<Parameter> parameters;

	/**
	 * 接口模型
	 * 
	 * @param url
	 *            接口地址
	 * @param method
	 *            请求方式
	 * @param parameterType
	 *            参数模型
	 * @param parameters
	 *            参数集
	 * @param arrays
	 *            JSON 数组集
	 */
	public Interface(String url, Method method, ParType parameterType, Parameteres parameters, Arrays arrays) {
		this.url = url;
		this.method = method.name();
		this.parameterType = parameterType.name();
		if (parameters != null) {
			this.parameters = parameters.getPes();
		}
		if (arrays != null) {
			this.arrays = arrays.getArrays();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public List<Array> getArrays() {
		return arrays;
	}

	public void setArrays(List<Array> arrays) {
		this.arrays = arrays;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}

package com.sooncode.verification.moduler;

import com.sooncode.verification.enumes.DataType;

/**
 * 参数 模型
 * @author pc
 *
 */
 public class Parameter {
	/**参数 KEY*/
	private String key ;
	
	/**参数验证类型*/
	private String type ;
	
	/**参数值 最大长度*/
	private Integer maxLength;
	
	/**是否必须*/
	private Boolean isNeed;
	
	/**值域*/
	private Object[] values;

	/**
	 * 参数
	 * @param key 参数名称
	 * @param type 测试类型
	 * @param maxLength 最大长度
	 * @param values 参数值域
	 */
	public Parameter(String key,String type,Integer maxLength, Object[] values){
	this.key = key ;
	this.type = type;
	this.maxLength = maxLength;
	this.isNeed = true;
	this.values=values;
	}
	/**
	 * 参数
	 * @param key 参数名称
	 * @param type 测试类型
	 * @param maxLength 最大长度
	 * @param values 参数值域
	 */
	public Parameter(String key,DataType type,Integer maxLength, Object[] values){
		this.key = key ;
		this.type = type.name();
		this.maxLength = maxLength;
		this.isNeed = true;
		this.values=values;
	}
	
	
	 
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

 

	public Integer getMaxLength() {
		return maxLength;
	}


	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}


	public Boolean getIsNeed() {
		return isNeed;
	}


	public void setIsNeed(Boolean isNeed) {
		this.isNeed = isNeed;
	}


	public Object[] getValues() {
		return values;
	}


	public void setValues(String[] values) {
		this.values = values;
	}


	 
	
}
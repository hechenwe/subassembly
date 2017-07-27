package com.sooncode.subassembly.reflect;

import java.io.Serializable;

public class Clazz implements Serializable {
	private static final long serialVersionUID = 214009345277967361L;
	
	private String clazzName ;
	
	private Integer clazzSize;

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public Integer getClazzSize() {
		return clazzSize;
	}

	public void setClazzSize(Integer clazzSize) {
		this.clazzSize = clazzSize;
	}
	
	

}

package com.sooncode.subassembly.reflect;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 测试反射 用到的类
 * 
 * @author pc
 *
 */
public class Student extends Persion implements Serializable {
	private static final long serialVersionUID = -3416032541178594334L;
	 
	private int age;

	private Integer hight;
	private List<String> adress;

	private Map<String, String> map;

	private Clazz clazz;
	
	public Student() {

	}
 
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getAdress() {
		return adress;
	}

	public void setAdress(List<String> adress) {
		this.adress = adress;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Integer getHight() {
		return hight;
	}

	public void setHight(Integer hight) {
		this.hight = hight;
	}
	
	

}

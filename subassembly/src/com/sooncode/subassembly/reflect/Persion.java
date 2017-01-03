package com.sooncode.subassembly.reflect;

import java.util.List;
import java.util.Map;

/**
 * 测试反射 用到的类
 * 
 * @author pc
 *
 */
public class Persion {
	private String name;
	private int age;
	
	private List<String> adress;
	
	private Map<String,String> map;
	
	public Persion() {

	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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

	
	 
	 
}

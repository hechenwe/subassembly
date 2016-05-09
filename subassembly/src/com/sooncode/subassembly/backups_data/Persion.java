package com.sooncode.subassembly.backups_data;

import java.io.Serializable;
import java.util.Date;

public  class Persion implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name ;
	private String sex ;
	 
	private Integer age ;
	private Date birthday;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	 
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"sex\":\"" + sex + "\",\"age\":\"" + age + "\",\"birthday\":\"" + birthday + "\"}  ";
	}
	 
	
}

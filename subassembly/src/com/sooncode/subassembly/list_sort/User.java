package com.sooncode.subassembly.list_sort;

public class User{

	private String userName;
	private Integer age;
	private Integer hight ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getHight() {
		return hight;
	}
	public void setHight(Integer hight) {
		this.hight = hight;
	}
	public User(String userName, Integer age, Integer hight) {
		super();
		this.userName = userName;
		this.age = age;
		this.hight = hight;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", age=" + age + ", hight=" + hight + "]";
	}
	
	 
	 
}

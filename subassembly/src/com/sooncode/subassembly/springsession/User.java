package com.sooncode.subassembly.springsession;

public class User {
	String userName;
	String userId;

	public User(String userName, String userId) {
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
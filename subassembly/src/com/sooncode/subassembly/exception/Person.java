package com.sooncode.subassembly.exception;

class Person {
	private int age;

	public void setAge(int age) {
		if (age > 100) {
			throw new AgeTooOldException("年龄过大");
		} else if (age < 0) {
			throw new AgeTooYoungException("年龄太小了!");
		}
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}


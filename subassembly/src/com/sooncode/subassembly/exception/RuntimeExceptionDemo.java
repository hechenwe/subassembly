package com.sooncode.subassembly.exception;

class RuntimeExceptionDemo {
	public static void main(String[] args) {
		Person p = new Person();
		p.setAge(-1);
		p.getAge();
	}
}
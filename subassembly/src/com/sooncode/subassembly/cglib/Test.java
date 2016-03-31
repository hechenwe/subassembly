package com.sooncode.subassembly.cglib;

public class Test {
	public static void main(String[] args) {

		Student s = new Student("123454");
		Clazz c = s.getClazz();// 访问延迟加载对象
		System.out.println(c.getClassName());
	}
}

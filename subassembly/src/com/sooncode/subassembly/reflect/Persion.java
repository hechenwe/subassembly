package com.sooncode.subassembly.reflect;

/**
 * 测试反射 用到的类
 * 
 * @author pc
 *
 */
public class Persion {
	public Persion() {

	}

	public Persion(String name) {
		this.name = name;
	}

	public Persion(Integer age) {
		this.age = age;
	}

	public Persion(String name, Integer age) {
		this.age = age;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "[" + this.name + "  " + this.age + "]";
	}

	private String name;
	private int age;

	public static Integer add(Integer a, Integer b) {
		return a + b;
	}

	public String getSum(Integer a, String b) {
		return b + ":" + a;
	}
}

package com.sooncode.subassembly.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 学生类
 * 
 * @author pc
 *
 */
public class Student {

	private String studentId;
	private String name;
	private String sex;
	private Integer age;

	private String classId;

	private Clazz clazz;

	protected Clazz createClazz() {
		JdbcCache ccll=	new JdbcCache();
		ccll.setObject(this.studentId) ;
		return (Clazz) Enhancer.create(Clazz.class,ccll);
	}

	public Student(String studentId) {
		this.studentId = studentId;
		this.clazz = createClazz();
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", sex=" + sex + ", age=" + age + ", classId=" + classId + "]";
	}
}

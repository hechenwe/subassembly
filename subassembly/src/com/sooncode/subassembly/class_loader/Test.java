package com.sooncode.subassembly.class_loader;

public class Test {

	 public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		 MyClassLoader mcl = new MyClassLoader();
		 Class<?> clas = mcl.loadClass4This("E:/com/sooncode/subassembly/class_loader/Teacher.class");
		 System.out.println(clas.newInstance());
		 
		  
	}
	 
}

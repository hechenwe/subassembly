package com.sooncode.subassembly.soontest;

public class Comput implements ComputInterface{
	public Integer add(Integer a, Integer b) {
		return a + b;
	}
	
	
	public static void main(String[] args) {
		OpenInterfaceTest oit  = new OpenInterfaceTest();
		 
		ComputInterface ci =   oit.newInstance4Test(Comput.class);
		ci.add(100, 200);
	}
}

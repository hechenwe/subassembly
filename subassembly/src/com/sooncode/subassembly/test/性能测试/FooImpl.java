package com.sooncode.subassembly.test.性能测试;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FooImpl implements Foo {
	
	
	private List<?> link = new LinkedList<>();
	private List<?> array = new ArrayList<>();

	public FooImpl() {
		for (int i = 0; i < 10000; i++) {
			array.add(new Integer(i), null);
			link.add(new Integer(i), null);
		}
	}

	public void testArrayList() {
		for (int i = 0; i < 10000; i++)
			array.get(i);
	}

	public void testLinkedList() {
		for (int i = 0; i < 10000; i++)
			link.get(i);
	}
}
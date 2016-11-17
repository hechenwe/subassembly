package com.sooncode.subassembly.test.性能测试;

public class TestProxy {
	public static void main(String[] args) {

		Foo foo = (Foo) OpenInterfaceTest.newInstance(new FooImpl());
		foo.testArrayList();
		foo.testLinkedList();

	}
}
package com.sooncode.subassembly.manythread.runnable;

import java.util.ArrayList;
import java.util.List;

public class TestThread implements Runnable {
    private static List<String> strings ;
	@Override
	public void run() {
		List<String> temp = new ArrayList<>();
		String str = "haha";
		temp.add(str);
		strings.addAll(temp);
		System.out.println("开始工作");
		
	}

	public static void main(String[] args) throws InterruptedException {
		TestThread myThread = new TestThread();  
		Thread thread1 = new Thread(myThread);  
		Thread thread2 = new Thread(myThread);  
		Thread thread3 = new Thread(myThread);  
		thread1.start();  
		thread2.start();  
		thread3.start();  
		
		
		Thread.sleep(3000);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
}

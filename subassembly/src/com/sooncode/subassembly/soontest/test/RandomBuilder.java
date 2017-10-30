package com.sooncode.subassembly.soontest.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomBuilder {
	
	private Map<Integer,Integer> numbers;
	private int min ;
	private int max;
	public static int random(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}
	
	
	public static double random() {
		Random random = new Random();
		return random.nextDouble();
	}

	public  RandomBuilder(int min , int max) {
		this.min = min;
		this.max = max;
		Map<Integer,Integer> map = new HashMap<>();
		for(int i = 0 ;i < max - min + 1 ; i++) {
			map.put(min + i,min + i);
		}
		this.numbers = map;
	}
	
	public Integer nextRandom() {
		
		
		while ( this.numbers.size() > 0 ) {
			int i = random(this.min ,this.max);
			Integer n = this.numbers.get(i);
			if(n != null) {
				this.numbers.remove(n);
				return n ;
			}
		}
		return null;
	}
	 
	
	
	public static void main(String[] args) {
		RandomBuilder rb = new RandomBuilder(1, 10);
		for (int i = 0; i < 12; i++) {
			System.out.println("RandomBuilder.main()"+ rb.nextRandom());
		}
	}

}

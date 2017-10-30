package com.sooncode.subassembly.list_sort;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestMapSort {
public static void main(String[] args) {
	
		Map<Integer,User> map = new HashMap<>();
		map.put( 1 , new User("A", 3, 4));
		map.put( 2 , new User("A", 2, 7));
		map.put( 3 , new User("A", 1, 5));
		
		LinkedHashMap<Integer, User> m = new MapSort<Integer,User>() {

			@Override
			public boolean compare(Integer k1, Integer k2, User v1, User v2) {
				int n1 = k1+v1.getAge() + v1.getHight();
				int n2 = k2+v2.getAge() + v2.getHight();
				
				
				return (n1 <= n2);
			}

			 
		}.sort(map);
		
		System.out.println(m);
	}
	
}

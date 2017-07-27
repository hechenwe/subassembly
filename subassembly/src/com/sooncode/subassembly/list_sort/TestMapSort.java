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
				 
				return (v1.getHight() <= v2.getHight());
			}

			 
		}.sort(map);
		
		System.out.println(m);
	}
	
}

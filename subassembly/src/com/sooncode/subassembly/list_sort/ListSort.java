package com.sooncode.subassembly.list_sort;

 

import java.util.LinkedList;
import java.util.List;

public abstract class ListSort <M>{

	
	public  LinkedList<M>  sort (List<M> list){
		
		@SuppressWarnings("unchecked")
		M [] ms =  (M[]) list.toArray();
		int length = ms.length;
		for (int i = 0 ; i < length ; i++) {
			 for(int j = i+1 ; j < length  ; j++) {
				 M m1 = ms[i];
				 M m2 = ms[j];
				 if(this.compare(m1, m2)) {
					 M tempM = ms[i];
					 ms[i] = ms[j];
					 ms[j] = tempM;
				 }
			 }
		}
		LinkedList<M> newList = new LinkedList<>();
		for (M m : ms) {
			newList.add(m);
		}
		return newList;
	}
	
	
	
	public abstract boolean compare(M m1 ,M m2);
	
}

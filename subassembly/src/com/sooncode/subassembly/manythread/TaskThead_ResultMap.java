package com.sooncode.subassembly.manythread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 任务线程 返回值为map
 * 
 * @author pc
 *
 */
public class TaskThead_ResultMap implements Callable<Map<String,String>> {

	 
	
	
    /**
     * 线程数量
     */
	private int threadNum;
    
	public TaskThead_ResultMap(int i ){
		this.threadNum = i;
	}
	/**
	 * 多线程运行任务
	 * @return 多线程获得的结果
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public Map<String,String> getResultThead () throws InterruptedException, ExecutionException{
	ExecutorService pool = Executors.newFixedThreadPool(threadNum);// 创建一个线程池
	    Map<String,String> map = new HashMap<>();// 创建多个有返回值的任务
		 
		for (int i = 0; i < threadNum; i++) {
			TaskThead_ResultMap c = new TaskThead_ResultMap(5);
			
			Future<Map<String,String>> future= pool.submit(c);// 执行任务并获取Future对象
			map.putAll(future.get());
			
			//list.add(future); //将多线程返回的对象收集在一起
		}
		
		pool.shutdown();// 关闭线程池
		return map;
	}
	
	
	/**
	 * 任务
	 */
	@Override
	public Map<String,String> call() throws Exception {
		Map<String,String> map = new HashMap<>();
		//Thread.sleep(1000);
		int d =(int) (Math.random()*10);
		map.put(d+"", d+"");
		return map;
	}
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TaskThead_ResultMap tt = new TaskThead_ResultMap(20);
		Map<String,String> map =   tt.getResultThead();
			for (String future : map.keySet()) {
				System.out.println("TaskThead.main()"+future);
				
			}	
	}

	 
	
}

package com.sooncode.subassembly.manythread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.text.StyledEditorKit.StyledTextAction;

/**
 * 任务线程 返回值为list
 * 
 * @author pc
 *
 */
public class TaskThead_ResultList implements Callable<Object> {

	 
	
	
    /**
     * 线程数量
     */
	private String threadNum;
    
	public TaskThead_ResultList(String i ){
		this.threadNum = i;
	}
	/**
	 * 多线程运行任务
	 * @return 多线程获得的结果
	 */
	public List<Future<Object>> getResultThead (){
	ExecutorService pool = Executors.newFixedThreadPool(4);// 创建一个线程池
		long t1 = System.currentTimeMillis();//计时
		List<Future<Object>> list = new ArrayList<Future<Object>>();// 创建多个有返回值的任务
		for (int i = 0; i < 4; i++) {
			
			TaskThead_ResultList c = new TaskThead_ResultList(i+"");
			
			Future<Object> f = pool.submit(c);// 执行任务并获取Future对象
			 
			list.add(f); //将多线程返回的对象收集在一起
		}
		
		pool.shutdown();// 关闭线程池
		 
		System.out.println("启动线程所用时间:"+ (System.currentTimeMillis()-t1));
		return list;
	}
	
	
	/**
	 * 任务
	 */
	@Override
	public Object call() throws Exception {
	long t1 =	System.currentTimeMillis();
		System.out.println(threadNum + "个线程进入运行状态..." + t1);
		int d = 0;
		for(int i = 0 ;i<100;i++){
			d =d +(int) (Math.random()*10);
			System.out.println(threadNum+"线程  正在执行任务第"+i+"个任务....");
		}
		System.out.println(threadNum + "个线程进入完成任务用时:" + (System.currentTimeMillis()-t1));
		return d;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TaskThead_ResultList tt = new TaskThead_ResultList("3");
		List<Future<Object>> listes =   tt.getResultThead();
			for (Future<Object> future : listes) {
				System.out.println("结果:"+future.get());
				
			}	
	}

	 
	
}

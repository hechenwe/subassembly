package com.sooncode.subassembly.soontest.test;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sooncode.subassembly.pay.OrderUtil;
import com.sooncode.subassembly.soontest.CallabbleMethod;
import com.sooncode.subassembly.soontest.OpenInterfaceTest;
import com.sooncode.subassembly.soontest.SoonTest;

/**
 * 多线程测试
 * 
 * @author pc
 *
 */
public class ManyTheadTest implements SoonTest  {

	/**
	 * 线程数量
	 */
	private int threadNum;
	
	/**执行数量*/
	private int executeNum;
	 
    /**
     * 创建多线程测试实例
     * @param threadNum 线程数量
     * @param executeNum 执行数量
     */
	public ManyTheadTest(int threadNum ,int executeNum) {
		this.threadNum = threadNum;
		this.executeNum = executeNum;
		
	}

	 
	
	/**
	 * 多线程运行任务
	 * 
	 * @return 多线程获得的结果
	 */
	public List<Object> testMethod(Class<?> testClass, String methodName, Object[] agrs) {
		ExecutorService pool = Executors.newFixedThreadPool(this.threadNum);// 创建一个线程池
	 
		CallabbleMethod method = new CallabbleMethod(testClass, methodName, agrs);
		List<Object> list = new LinkedList<>();
		for (int i = 0; i < this.executeNum; i++) {
			try {
				Future<Object> future = pool.submit(method);// 执行任务并获取Future对象
				Object obj = future.get();
				if(obj!=null){
					//--------------释放资源---------------
					if(obj instanceof Connection){
						((Connection) obj).close();
					}
					//----------------------------
					list.add( obj );
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();// 关闭线程池
		return list;
	}

	 

	public static void main1(String[] args) throws InterruptedException, ExecutionException {
		ManyTheadTest mtt = new ManyTheadTest(5,20);
		OpenInterfaceTest oit = new OpenInterfaceTest();
		SoonTest st = oit.newInstance4Test(mtt);
		List< Object > list = st.testMethod( OrderUtil.class,"getOrderNumber",new String[]{"EDU"});
		for ( Object  obj : list) {
			System.out.println("结果:" + obj);
		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ManyTheadTest mtt = new ManyTheadTest(20,20);
		 
		OpenInterfaceTest oit = new OpenInterfaceTest();
		SoonTest st = oit.newInstance4Test(mtt);
		
		//List< Object > list0 = st.testMethod( SingletonManager.class,"setAge",new Object [] {RandomBuilder.random(0, 100)});
		List< Object > list = st.testMethod( SingletonManager.class,"getSingletonManager",new Object [] { new DataModel()});
		for ( Object  obj : list) {
			System.out.println("----结果:" + obj);
			SingletonManager sm = (SingletonManager) obj;
			DataModel dm = sm.getDataModel();
			dm.setName("XX"+ RandomBuilder.random(23, 123) );
			System.out.println("结果:----" + sm.getDataModel());
		}
	}

}

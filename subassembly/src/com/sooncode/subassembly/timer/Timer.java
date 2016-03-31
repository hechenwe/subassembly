package com.sooncode.subassembly.timer;

import java.util.Date;

/**
 * 定时器
 * 
 * @author pc
 *
 */
public class Timer {
	/**
	 * 定时执行的方法
	 */
	public void doSome() {
		System.out.println("Timer.doSome()" + new Date());
	}
}

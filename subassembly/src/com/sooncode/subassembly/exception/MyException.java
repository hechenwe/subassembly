package com.sooncode.subassembly.exception;

  //public class MyException1 extends Exception {
	public class MyException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException() {

	}

	public MyException(String meg) {
		super(meg);
	}
	public MyException(Throwable t) {
		super(t);
	}
	public MyException(String meg,Throwable t) {
		super(meg,t);
	}

}

package com.sooncode.subassembly.exception;
/*
 * 含有异常的业务类
 */
public class HaveException {
	 
	    public int shang(int x,int y) throws MyException 
	    {
	        if(y<0)
	        {
	            throw new MyException("您输入的是"+y+",规定除数不能为负数!");//抛出异常

	        }
	        if(y==0)
	        {
	            throw new MyException("您输入的是"+y+",除数不能为0!");
	        }
	    
	        int m=x/y;
	        return m;
	    }
	 
}

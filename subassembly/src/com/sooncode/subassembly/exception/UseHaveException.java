package com.sooncode.subassembly.exception;

public class UseHaveException {
	public static void main(String[] args) {

		HaveException n=new HaveException();
        try {
			n.shang(1, 1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //捕获异常

        try
        {
            System.out.println("商="+n.shang(1,-3));
        }
        catch(MyException yc)
        {
            System.out.println(yc.getMessage());
            yc.printStackTrace();
        }
         
        catch(Exception y)
        {
            System.out.println(y.getMessage());
            y.printStackTrace();
        }
    
    finally{ System.out.println("finally!");} ////finally不管发没发生异常都会被执行    


    }	 
}

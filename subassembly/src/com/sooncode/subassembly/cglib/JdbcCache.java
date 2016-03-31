package com.sooncode.subassembly.cglib;

import net.sf.cglib.proxy.LazyLoader;

public class JdbcCache implements LazyLoader  { 
	private  Object id;
	public void setObject(Object id){
		this.id = id;
	}
    public Object loadObject() throws Exception { 
    	
        System.out.println("创建 班级" + this.id);
        Clazz clazz = new Clazz();  
        clazz.setClassId((String) this.id);
        
        
        return clazz;  
    }  
}  
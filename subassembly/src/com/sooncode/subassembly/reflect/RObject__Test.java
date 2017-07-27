package com.sooncode.subassembly.reflect;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class RObject__Test {
	public static Logger logger = Logger.getLogger("RObject__Test.class");
    @Test
	public void newRObject1(){
		RObject<Student> rObj = new RObject<>(Student.class);
		List<Field> list = rObj.getFields();
		for (Field field : list) {
			logger.info(field.getType().getName() + " : "+  field.getName());
			
		}
	}
    
    
    @Test
    public void newRObject2(){
    	RObject<Student> rObj = new RObject<>("com.sooncode.subassembly.reflect.Student");
    	List<Field> list = rObj.getFields();
    	for (Field field : list) {
    		logger.info(field.getType().getName() + " : "+  field.getName());
    	}
    }
    @Test
    public void newRObject3(){
    	
    	RObject<Student> rObj = new RObject<>(new Student());
    	List<Field> list = rObj.getFields();
    	for (Field field : list) {
    		logger.info(field.getType().getName() + " : "+  field.getName());
    		
    	}
    }
    
    public void haveField(){
    	RObject<Student> rObj = new RObject<>(Student.class);
    	boolean b1 = rObj.hasField("sfd");
    	boolean b2 = rObj.hasField("age");
    	
    	logger.info(b1 + "　：　"+ b2);
    	
    }
}

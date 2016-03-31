package com.sooncode.subassembly.dictionary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

 

import org.apache.log4j.Logger;

import com.sooncode.subassembly.dictionary.entity.Groups;

/**
 * 对象持久化(将对象保存到磁盘上)
 * @author pc
 *
 */
public class SaveObjectUtil {
	
	 public final static Logger logger = Logger.getLogger("SaveObjectUtil.class");
	 
	 
	 /**
	  * 把对象保存到磁盘中
	  * @param fileName 文件全名
	  * @param obj 序列化的对象,List,Map 等
	  */
	 public static void saveObject(String fileName , Object obj)
	    {
	        File file =new File(fileName);
	        FileOutputStream out;
	        try {
	            out = new FileOutputStream(file);
	            ObjectOutputStream objOut=new ObjectOutputStream(out);
	            objOut.writeObject(obj);
	            objOut.flush();
	            objOut.close();
	            //logger.info("write object success!");
	        } catch (IOException e) {
	        	//logger.info("write object failed");
	            e.printStackTrace();
	        }
	    }
	 
	 
	 /**
	  * 从磁盘中读取对象
	  * @param fileName 文件全名
	  * @return obj 序列化的对象,List,Map 等
	  */
	 public static Object readObject(String fileName)
	    {
	        Object temp=null;
	        File file =new File(fileName);
	        FileInputStream in;
	        try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn=new ObjectInputStream(in);
	            temp=objIn.readObject();
	            objIn.close();
	            //logger.info("read object success!");
	        } catch (IOException e) {
	        	 //logger.info("read object failed");
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return temp;
	    }
	 
	 
	 public static void main(String[] args) {
		Groups g = new Groups();
		g.setGroupId(12);
		g.setGroupCode("hechen");
		g.setGroupName("coocle");
		g.setGroupState(1);
		
		Map<String,Groups> map = new HashMap<>();
		map.put(g.getGroupCode(),g);
		 //saveObject("d:/hechen.dat",map);
		 //logger.info(readObject("d:/hechen.dat"));
		
		
	}
}

package com.sooncode.subassembly.class_loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import com.sooncode.subassembly.file.FileUtil;

public class MyClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		
		return super.loadClass(name);
	}
	
 
	public Class<?> loadClass4This(String filePathName) throws ClassNotFoundException {
		
		FileUtil fu = new FileUtil();
		String str;
		try {
			byte [] b = getClassFileBytes(filePathName);
			//str = fu.readFile(filePathName);
			//System.out.println(">"+str+"<");
			return this.defineClass("com.sooncode.subassembly.class_loader.Teacher", b, 0, b.length);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		 
		
		
		
		
	}
	
	 private byte[] getClassFileBytes(String classFile) throws Exception {
	        FileInputStream fis = new FileInputStream(classFile );
	        FileChannel fileC = fis.getChannel();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        WritableByteChannel outC = Channels.newChannel(baos);
	        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	        while (true) {
	            int i = fileC.read(buffer);
	            if (i == 0 || i == -1) {
	                break;
	            }
	            buffer.flip();
	            outC.write(buffer);
	            buffer.clear();
	        }
	        fis.close();
	        return baos.toByteArray();
	    }

}

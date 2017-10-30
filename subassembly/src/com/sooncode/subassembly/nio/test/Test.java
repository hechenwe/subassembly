package com.sooncode.subassembly.nio.test;

 
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class Test {

	public static void main(String[] args) throws Exception {
		 
		FileChannel readFC = NIO.getFileChannel("e:/辩护人.BD1280高清韩语中字.mp4", StandardOpenOption.READ);
		final FileChannel writeFC = NIO.getFileChannel("e:/1.mp4", StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);
		//String data = nio.read("e:/Teacher.java");
		//NIO.write("e:/Teacher.txt", "hello -- 你好");
		 
		NIO.readBigFile(readFC, 1024, new  DataProcessor() {

			@Override
			public void disposeData(byte[] onceReadData) throws IOException {
					writeFC.write( ByteBuffer.wrap(onceReadData));
			}
			
		}); 
	}
}

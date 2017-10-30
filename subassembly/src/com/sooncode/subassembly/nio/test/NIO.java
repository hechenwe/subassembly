package com.sooncode.subassembly.nio.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class NIO {

	private static final int ALLOCATE = 1024;

	public static FileChannel getFileChannel(String path, StandardOpenOption... standardOpenOptions) {
		try {
			return FileChannel.open(Paths.get(path), standardOpenOptions);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static FileChannel getFileChanael(FileInputStream fis) {
		return fis.getChannel();
	}

	public static FileChannel getFileChanael(FileOutputStream fos) {
		return fos.getChannel();
	}

	public static String read(String path) {

		FileChannel fileChannel = getFileChannel(path, StandardOpenOption.READ);
		String data = new String();
		ByteBuffer byteBuffer = ByteBuffer.allocate(ALLOCATE);
		try {
			while (fileChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				data += new String(byteBuffer.array());
				byteBuffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ResourceRelease.closes(fileChannel);
		}

		return data;

	}
	
	/**
	 * 读取大文件
	 * @param readChannel
	 * @param onceReadSize
	 * @param DataProcessor
	 */
	public static void readBigFile(FileChannel  readChannel , int onceReadSize , DataProcessor  DataProcessor ) {
		  
		ByteBuffer byteBuffer = ByteBuffer.allocate(onceReadSize);
		try {
			while (readChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				DataProcessor.disposeData(byteBuffer.array());
				byteBuffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ResourceRelease.closes(readChannel);
		}
		 
	}

	public static void copy(FileChannel fileChannel, FileChannel writeChannel, int byteBufferAllocate) {

		ByteBuffer byteBuffer = ByteBuffer.allocate(byteBufferAllocate);
		try {
			while (fileChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				writeChannel.write(byteBuffer);
				byteBuffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ResourceRelease.closes(fileChannel, writeChannel);
		}

	}

	public static int write(FileChannel fileChannel, byte[] data) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(data);
		try {
			return fileChannel.write(byteBuffer);
		} catch (IOException e) {

			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	

	public  static int write(String path, String data) {

		FileChannel fileChannel = getFileChannel(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
		ByteBuffer byteBuffer = ByteBuffer.wrap(data.getBytes());
		try {
			return fileChannel.write(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

}

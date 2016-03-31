package com.sooncode.subassembly.path;

public class PathUtil {
	private static String src;
	private static String webRoot;

	public static String getSrc() {
		src = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "";
		return src;
	}

	public static String getWebRoot() {
		webRoot = System.getProperty("goodstudent.root");
		return webRoot;
	}
	
	public static void main(String[] args) {
		getSrc();
	}

}

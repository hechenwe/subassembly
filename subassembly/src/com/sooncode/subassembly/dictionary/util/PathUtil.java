package com.sooncode.subassembly.dictionary.util;

public class PathUtil {
	private String src;
	private String webRoot;

	public String getSrc() {
		src = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "";
		return src;
	}

	public String getWebRoot() {
		webRoot = System.getProperty("goodstudent.root");
		return webRoot;
	}

}

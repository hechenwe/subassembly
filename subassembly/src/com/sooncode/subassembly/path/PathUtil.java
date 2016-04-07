package com.sooncode.subassembly.path;

import java.io.File;

public class PathUtil {

	public static String getSrc() {
		return new PathUtil().getClassesPath();
	}

	private String getClassesPath() {
		String path = this.getClass().getResource("/").getPath();
		File file = new File(path);
		String classesPath = file.toString() + File.separatorChar;
		return classesPath;
	}
}

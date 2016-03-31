package com.sooncode.subassembly.word;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sooncode.subassembly.freemarker.FreemarkerUtil;

public class TestWord {

	public static Logger logger = Logger.getLogger("TestWord.class");
    
	public static void main(String[] args)   {
    Map<String,Object> map = new HashMap<>();
    map.put("title","标题标题");
    String templatePath = "d:";
    String templateName = "test.xml";
    String outPath = "d:";
    String outFileName = "test.doc";
    
    String encoding = "utf-8";
    try {
		FreemarkerUtil.fileOutput(templatePath, templateName, outPath, outFileName, map, encoding);
	} catch (Exception e) {
	 
		e.printStackTrace();
	}
    
    
	}
}

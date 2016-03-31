package com.sooncode.subassembly.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.log4j.Logger;

 

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
/**
 * Freemarker 工具类
 */
public class FreemarkerUtil {
 
	public static Logger logger = Logger.getLogger("FreemarkerUtil.class");

	/**
	 * 根据模板生成文件并输出
	 * @param templatePath
	 * @param templateName
	 * @param outPath
	 * @param outFileName
	 * @param data
	 * @param encoding
	 * @throws Exception
	 */
	public static void fileOutput(String templatePath,String templateName,String outPath,String outFileName,Map<String,Object> data,String encoding) throws Exception {
		
		Configuration configuration = new Configuration();
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setDefaultEncoding(encoding);
		File tempfile = new File(templatePath);//模板所在的位置
		configuration.setDirectoryForTemplateLoading(tempfile);
		Template template = configuration.getTemplate(templateName);// 模板名称
		File folder = new File(outPath); //输出文件所在的位置
		 
		boolean isExist = folder.exists();
		if (isExist == false) { // 该文件夹不存在
			folder.mkdirs(); // 创建文件夹
			isExist = true;
		}
		if (isExist) {
			File file = new File( outPath + "/" + outFileName);// 要输出的文件
			Writer riter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			template.process(data, riter);
			riter.flush();
			riter.close();
		}
	}
}

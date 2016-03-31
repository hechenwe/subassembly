package com.sooncode.subassembly.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml 解析工具类
 * 
 * @author pc
 *
 */
public class Dom4jUtil {
	public static void main2(String[] args) throws Exception {
		// 第一种方式：创建文档，并创建根元素
		// 创建文档:使用了一个Helper类
		Document document = DocumentHelper.createDocument();

		// 创建根节点并添加进文档
		Element root = DocumentHelper.createElement("student");
		document.setRootElement(root);

		// 第二种方式:创建文档并设置文档的根元素节点
		Element root2 = DocumentHelper.createElement("student");
		Document document2 = DocumentHelper.createDocument(root2);

		// 添加属性
		root2.addAttribute("name", "zhangsan");
		// 添加子节点:add之后就返回这个元素
		Element helloElement = root2.addElement("hello");
		Element worldElement = root2.addElement("world");

		helloElement.setText("hello Text");
		worldElement.setText("world text");

		// 输出
		// 输出到控制台
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);

		// 输出到文件
		// 格式
		OutputFormat format = new OutputFormat("    ", true);// 设置缩进为4个空格，并且另起一行为true
		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("student.xml"), format);
		xmlWriter2.write(document2);

		// 另一种输出方式，记得要调用flush()方法,否则输出的文件中显示空白
		XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student2.xml"), format);

		xmlWriter3.write(document2);
		xmlWriter3.flush();
		// close()方法也可以

	}

	public static void main(String[] args) throws Exception {
		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(new File("config/applicationContext.xml"));

		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());

		// 获取所有子元素
		List<Element> childList = root.elements();
		for (Element element : childList) {
			Attribute attr1 = element.attribute("id");
			System.out.println("total child count: " + element.getName() +" -- "+ attr1.getValue());
			
		}

		// 获取特定名称的子元素
		List<Element> childList2 = root.elements("bean");
		System.out.println("hello child: " + childList2.size());

		// 获取名字为指定名称的第一个子元素
		Element firstWorldElement = root.element("world");
		// 输出其属性
	//	System.out.println("first World Attr: " + firstWorldElement.attribute(0).getName() + "=" + firstWorldElement.attributeValue("name"));

		//System.out.println("迭代输出-----------------------");
		// 迭代输出
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			//Element e = (Element) iter.next();
			//System.out.println(e.attributeValue("name"));

		}

		System.out.println("用DOMReader-----------------------");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 注意要用完整类名
		//org.w3c.dom.Document document2 = db.parse(new File("students.xml "));

	//	DOMReader domReader = new DOMReader();

		// 将JAXP的Document转换为dom4j的Document
	//	Document document3 = domReader.read(document2);

	//	Element rootElement = document3.getRootElement();

	//	System.out.println("Root: " + rootElement.getName());

	}

}

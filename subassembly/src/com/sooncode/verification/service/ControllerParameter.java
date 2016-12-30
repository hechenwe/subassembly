package com.sooncode.verification.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
 
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sooncode.verification.moduler.Array;
import com.sooncode.verification.moduler.Controller;
import com.sooncode.verification.moduler.Parameter;

/**
 * 加载参数配置文件
 * 
 * @author pc
 *
 */
public class ControllerParameter {
    private Logger logger = Logger.getLogger("ControllerParameter.class");
	private   String path = PathUtil.getClassPath() + "\\";
	private   Document document;
	public  Map<String, Parameter> paraMap = new HashMap<>();
	public ControllerParameter(String paraXml)  {
		  
			String str = readFile(path+paraXml);
			InputSource inputSource = new InputSource(new StringReader(str));
			try {
				this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
			} catch (SAXException | IOException | ParserConfigurationException e) {

				e.printStackTrace();
			}

			this.initControllerMap();
		 
	}

	private  void initControllerMap() {

		NodeList root = this.document.getElementsByTagName("controllers");
		Node node = root.item(0); // NodeList中的某一个节点
		NodeList list = node.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			Node thisNode = list.item(i);
			String ke = thisNode.getNodeName();
			if (ke.equals("parameter")) {
				Parameter p = getParameter(thisNode);
				if (p != null) {
					 this.paraMap.put(p.getKey(), p);
				}
			}
			if (ke.equals("controller")) {
				Controller c = getController(thisNode);
				ControllerParameterManager.controllerMap.put(c.getUrl(), c);
				logger.debug("【参数加载】"+c.getUrl());
			}
		}

	}

	private   Controller getController(Node node) {
		NamedNodeMap nnm = node.getAttributes();
		RObject rObj = new RObject(Controller.class);
		for (int i = 0; i < nnm.getLength(); i++) {
			Node n = nnm.item(i);
			String name = n.getNodeName();
			String value = n.getNodeValue();
			rObj.invokeSetMethod(name, value);
		}
		Controller c = rObj.getObject();
		NodeList nodeList = node.getChildNodes();
		List<Parameter> parameters = new ArrayList<>();
		List<Array> arrays = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String nodeName = n.getNodeName();
			if (nodeName.equals("parameter")) {
				Parameter p = getParameter(n);
				parameters.add(p);
			} else if (nodeName.equals("array")) {
				Array a = getArray(n);
				arrays.add(a);
			}

		}
		c.setParameters(parameters);
		c.setArrays(arrays);
		return c;
	}

	private   Array getArray(Node node) {
		NamedNodeMap nnm = node.getAttributes();
		RObject rObj = new RObject(Array.class);
		for (int i = 0; i < nnm.getLength(); i++) {
			Node n = nnm.item(i);
			String name = n.getNodeName();
			String value = n.getNodeValue();
			rObj.invokeSetMethod(name, value);
		}

		Array a = rObj.getObject();

		NodeList nodeList = node.getChildNodes();
		List<Parameter> parameters = new ArrayList<>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n.getNodeName().equals("parameter")) {
				Parameter p = getParameter(n);
				parameters.add(p);
			}
		}
		a.setParameters(parameters);
		return a;
	}

	private   Parameter getParameter(Node node) {

		NamedNodeMap nnm = node.getAttributes();
		RObject rObj = new RObject(Parameter.class);
		String ref = null;
		for (int i = 0; i < nnm.getLength(); i++) {
			Node n = nnm.item(i);
			String name = n.getNodeName();
			String value = n.getNodeValue();

			if (name.equals("ref")) {
				ref = value;
			}

			Class<?> clas = rObj.getSetMethodParamertType(name);
			if (clas == Integer.class) {
				rObj.invokeSetMethod(name, Integer.parseInt(value));
			} else {
				rObj.invokeSetMethod(name, value);
			}
		}

		Parameter p = new Parameter();
		if (ref != null) {
			p = this.paraMap.get(ref);
		} else {
			p = rObj.getObject();
		}

		return p;

	}

	/**
	 * 读文件
	 * 
	 * @param filePath文件所在的路径
	 * @return 文件的内容
	 */
	private static String readFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists() || file.isDirectory()) {
			return null;
		}

		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				sb.append(temp + " ");
				temp = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	 
 

}

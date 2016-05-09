package com.sooncode.subassembly.jdk_xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class test1 {
	public static void main(String[] args) throws Exception {
		String xmlStr = "<WKHistory_List><WKHistory><WOCreationTime>2012-06-30</WOCreationTime><device>”NMS1;NE1;g0/1”</device><workDescription>this is demo work order</workDescription><workDetailID>001</workDetailID><workEndTime>2012-06-01</workEndTime><workID>wo123</workID><workName>test work order</workName><workStartTime>2012-06-31</workStartTime><worker>Woo, Chan</worker></WKHistory><WKHistory><WOCreationTime>2012-05-30</WOCreationTime><device>”NMS1;NE1;g0/1”</device><workDescription>this is demo work order</workDescription><workDetailID>001</workDetailID><workEndTime>2012-06-01</workEndTime><workID>wo123</workID><workName>test work order</workName><workStartTime>2012-05-31</workStartTime><worker>Woo, Chan</worker></WKHistory></WKHistory_List>";
		// 获得 节点 ipname2的值
		System.out.print(getParaValue(xmlStr));
	}

	/**
	 * XML文档的解析
	 */
	public static List<SDSWorkOrder> getParaValue(String xmlstr) throws Exception {
		List<SDSWorkOrder> list = new ArrayList<SDSWorkOrder>();
		InputSource is = new InputSource(new StringReader(xmlstr)); 
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is); //doc容器
		String paraname = "WKHistory";
		// 按文档顺序返回包含在文档中且具有给定标记名称的所有Element的NodeList
		NodeList nl = doc.getElementsByTagName(paraname);
		int beanNum = nl.getLength(); //获取NodeList数目
		for(int i=0;i<beanNum;i++){
			Node n = nl.item(i); //NodeList中的某一个节点
			// 获取节点的值
			SDSWorkOrder sdsworkorder = new SDSWorkOrder();
			sdsworkorder.setWo_creation_time(n.getChildNodes().item(0)
					.getTextContent());
			sdsworkorder.setDevice(n.getChildNodes().item(1)
					.getTextContent());
			sdsworkorder.setWork_description(n.getChildNodes().item(2)
					.getTextContent());
			sdsworkorder.setWork_detail_id(n.getChildNodes().item(3)
					.getTextContent());
			sdsworkorder.setWork_end_time(n.getChildNodes().item(4)
					.getTextContent());
			sdsworkorder.setWork_id(n.getChildNodes().item(5)
					.getTextContent());
			sdsworkorder.setWork_name(n.getChildNodes().item(6)
					.getTextContent());
			sdsworkorder.setWork_start_time(n.getChildNodes().item(7)
					.getTextContent());
			sdsworkorder.setWorker(n.getChildNodes().item(8)
					.getTextContent());
			list.add(sdsworkorder);
		}
		return list;
	}

}

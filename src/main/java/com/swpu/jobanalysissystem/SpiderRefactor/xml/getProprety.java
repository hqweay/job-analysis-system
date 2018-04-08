package com.swpu.jobanalysissystem.SpiderRefactor.xml;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class getProprety { 
	private static String getBean(String tag) {
		String xmlPath = "configure.xml";
		try {
			//构造解析树
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(new File(xmlPath));
			
			NodeList nl = doc.getElementsByTagName(tag);
			Node className = nl.item(0).getFirstChild();
			String value = className.getNodeValue();
			return value;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//数据存放路径
	public static String getPath(String path){
		return (String)getBean(path);
	}
	//爬虫执行的周期
	public static  int getPeriod(String period){
		return Integer.parseInt((String)getBean(period));
	}
	//返回爬虫类
	public static Object getSpider(String className){
		try{
		String value =  getBean(className);

		Class c = Class.forName(value);
		Object obj = c.newInstance();
		
		return obj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	//查看爬虫
	public static ArrayList<String> getSpiders(){
		try{
			String five = getBean("five");
			String zhi = getBean("zhi");
			ArrayList<String> spider = new ArrayList<>();
			spider.add(five);
			spider.add(zhi);
			return spider;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}

package com.swpu.jobanalysissystem.SpiderRefactor.xml;
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.transform.stream.*;
public class setProprety {
	private String xmlPath = "configure.xml";
	private void set(String tag,String value){
		try{
			//读取文档
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(new File(xmlPath));
			//获得节点的值
			NodeList nl = doc.getElementsByTagName(tag);
			nl.item(0).setTextContent(value);
			  TransformerFactory factor = TransformerFactory.newInstance();
	            Transformer former = (Transformer) factor.newTransformer();
	            former.transform(new DOMSource(doc), new StreamResult(new File(
	                    xmlPath)));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//设置存储路径
	public void setPaht(String path){
		this.set("path",path);
	}
	//设置周期
	public void setPeriod(String period){
		this.set("period",period);
	}
}

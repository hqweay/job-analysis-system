package com.swpu.jobanalysissystem.SpiderRefactor;

import com.swpu.jobanalysissystem.SpiderRefactor.Spider;
import com.swpu.jobanalysissystem.SpiderRefactor.xml.getProprety;


import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;

public class Task implements Runnable {
	private String SpiderName = "five";	//执行爬虫的类别
	//private String Path = "/home/silver/job/";   //爬虫爬下来的存储地址
	private String Path = "D:\\";   //爬虫爬下来的存储地址

	public void setSpiderName(String spiderName) {
		SpiderName = spiderName;
	}
	public void setPath(String path) {
		Path = path;
	}
	
	@Override
	public void run() {
		try{
			Spider spider= (Spider) getProprety.getSpider(SpiderName);
			spider.SetPath(Path);
			spider.run();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

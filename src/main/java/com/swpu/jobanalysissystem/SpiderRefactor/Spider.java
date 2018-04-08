package com.swpu.jobanalysissystem.SpiderRefactor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.alibaba.fastjson.JSONObject;
public abstract class Spider {
	private String path = null;
	public void SetPath(String path) {
		this.path = path;
	}
	public abstract void process(String url) ;
	public abstract void ProcessDetail(ArrayList<String> detail_urls);
	public void Save(ArrayList<JSONObject> offer) {
		try {
			Path dir = Paths.get(this.path);
			if(!Files.exists(dir)) {
				Files.createDirectory(dir);
			}
			Random random = new Random();
			int filename = random.nextInt(10000);
			FileOutputStream out = new FileOutputStream(new File(path+filename+".json"));
			OutputStreamWriter writer = new OutputStreamWriter( out,"utf-8");
			for(JSONObject e : offer) {
				writer.write(e.toJSONString()+"\n");
			}
			writer.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public Document getHtml(String url) {
		try {
			Document html = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
					.get();
			return html;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public abstract void run();
}

package com.swpu.jobanalysissystem.SpiderRefactor;

import java.util.ArrayList;

import com.swpu.jobanalysissystem.SpiderRefactor.Spider;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

public class ZhiSpider extends Spider {
	private String url= "http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E5%85%A8%E5%9B%BD&kw"
			+ "=%E5%A4%A7%E6%95%B0%E6%8D%AE%E5%BC%80%E5%8F%91&p=1&isadv=0";
	@Override
	public void process(String url) {
		try {
			ArrayList<String> urls = new ArrayList<>();
			Document html = getHtml(url);
			Elements zwmc = html.getElementsByClass("zwmc");
			for(Element e : zwmc) {
				String tmp_url = e.select("div > a").attr("href");
				urls.add(tmp_url);
			}
			ProcessDetail(urls);
			
			Elements next_page_urls = html.getElementsByClass("pagesDown-pos");
			if(!next_page_urls.isEmpty()) {
				String next_page_url = next_page_urls.get(0).child(0).attr("href");
				if(!next_page_url.isEmpty()) {
					process(next_page_url);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void ProcessDetail(ArrayList<String> detail_urls) {
		try {
			ArrayList<JSONObject> offer = new ArrayList<>();
			for(String single : detail_urls) {
				if(!single.isEmpty())
				{
					JSONObject tmp = new JSONObject();// ���ڴ�����ʱ�Ĺ�˾��Ϣ
					Document html = getHtml(single);
					Elements offer_details = html.getElementsByClass("terminal-ul clearfix");
					String job_url = single;
					String job_desc = html.select("div[class=tab-inner-cont]").text();
					String job_name = html.select("div[class=inner-left fl] > h1").text();
					if(!offer_details.isEmpty())
					{
						String job_salary = offer_details.get(0).child(0).select("strong").text();
						String job_place = offer_details.get(0).child(1).select("strong").text();
						String job_num = offer_details.get(0).child(6).select("strong").text();
						String min_xueli = offer_details.get(0).child(5).select("strong").text();
						String experience = offer_details.get(0).child(4).select("strong").text();
						Elements company_detail = html.select("ul[class=terminal-ul clearfix terminal-company mt20]");
						String company_size = company_detail.get(0).child(0).select("strong").text();
						String company_prop = company_detail.get(0).child(1).select("strong").text();
						String company_name = html.select("p[class=company-name-t]").text();
						String company_desc = html.select("div[class=tab-inner-cont]").get(1).text();
						tmp.put("job_name", job_name);
						tmp.put("job_salary", job_salary);
						tmp.put("job_desc", job_desc);
						tmp.put("job_place", job_place);
						tmp.put("job_num", job_num);
						tmp.put("min_xueli", min_xueli);
						tmp.put("experience", experience);
						tmp.put("company_name", company_name);
						tmp.put("company_size", company_size);
						tmp.put("company_prop", company_prop);
						tmp.put("job_url", job_url);
						tmp.put("company_desc", company_desc);
						offer.add(tmp);
					}
				}

			}
			Save(offer);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void run() {
		process(this.url);
	}
}

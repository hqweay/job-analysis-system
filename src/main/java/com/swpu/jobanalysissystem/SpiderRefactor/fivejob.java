package com.swpu.jobanalysissystem.SpiderRefactor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.swpu.jobanalysissystem.SpiderRefactor.Spider;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

public class fivejob extends Spider {
	private String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&keyword=%E5%A4%A7%E6%95%B0%E6%8D%AE&keywordtype=2"
			+"&lang=c&stype=2&postchannel=0000&fromType=1&confirmdate=9";
	@Override
	public void process(String url) {
		try {
			Document html = getHtml(url);
			ArrayList<String> offerUrls = new ArrayList<>();
			Elements zwmc = html.getElementsByClass("t1");
			if (!zwmc.isEmpty()) {
				for (Element e : zwmc) {
					String tmp_url = e.select("span > a").attr("href");
					if (!tmp_url.isEmpty()) {
						offerUrls.add(tmp_url);
					}
				}
				ProcessDetail(offerUrls);
				Elements next_page_urls = html.getElementsByClass("bk");
				if (!next_page_urls.isEmpty()) {
					String next_url = next_page_urls.get(1).select("a").attr("href");
					if (!next_url.isEmpty()) {
						process(next_url);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void ProcessDetail(ArrayList<String> detail_urls) {
		try {
			ArrayList<JSONObject> offer = new ArrayList<>();
			for (String single_url : detail_urls) {
				JSONObject tmp_json = new JSONObject();
				if (!single_url.isEmpty()) {
					Document html = getHtml(single_url);
					Elements offer_detail = html.select("div[class=t1]");
					String job_name = html.select("div[class=cn] > h1").text();
					String job_salary = html.select("div[class=cn] > strong").text();
					String job_place = html.select("div[class=cn] > span").text();
					String company_name = html.select("div[class=cn] > p > a").attr("title");
					String company_detail = html.select("p[class=msg ltype]").text().replace("|","").replaceAll("\\t", "");
                  System.out.println(company_detail);

					String company_prop = company_detail.substring(0,4);
					String regex = "\\d*-\\d*";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(company_detail);
					String company_size = "";
					if(m.find()) {
						company_size = m.group()+"人";
					}
					String job_desc = html.select("div[class=bmsg job_msg inbox]").text();
					String company_desc = html.select("div[class=tmsg inbox]").text();
					if(!offer_detail.isEmpty()){
						Element offer_details = offer_detail.get(0);
					if (offer_details.select("span[class=sp4]").size() > 3) {
						String experience = offer_details.child(0).text();
						String min_xueli = offer_details.child(1).text();
						
						String job_num = offer_details.child(2).text();
						tmp_json.put("job_place", job_place);tmp_json.put("job_salary", job_salary);tmp_json.put("min_xueli",min_xueli);
						tmp_json.put("job_name", job_name);tmp_json.put("job_num", job_num);tmp_json.put("company_desc",company_desc);
						tmp_json.put("job_desc", job_desc);tmp_json.put("company_name", company_name);tmp_json.put("experience",experience);
						tmp_json.put("job_url", url);tmp_json.put("company_prop",company_prop);tmp_json.put("company_size",company_size);
					}
					if (offer_details.select("span[class=sp4]").size() <= 3) {
						String experience = offer_details.child(0).text();
						String min_xueli = "无";
						String job_num = offer_details.child(1).text();
						tmp_json.put("job_place", job_place);tmp_json.put("job_salary", job_salary);tmp_json.put("min_xueli",min_xueli);
						tmp_json.put("job_name", job_name);tmp_json.put("job_num", job_num);tmp_json.put("company_desc",company_desc);
						tmp_json.put("job_desc", job_desc);tmp_json.put("company_name", company_name);tmp_json.put("experience",experience);
						tmp_json.put("job_url", url);tmp_json.put("company_prop",company_prop);tmp_json.put("company_size",company_size);
					}
				}
				}
				if(!tmp_json.isEmpty()){
					offer.add(tmp_json);
				}
			}
			Save(offer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {
		process(this.url);
	}
}

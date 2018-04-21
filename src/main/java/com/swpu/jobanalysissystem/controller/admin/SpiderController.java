package com.swpu.jobanalysissystem.controller.admin;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.swpu.jobanalysissystem.SpiderRefactor.Main;
import com.swpu.jobanalysissystem.SpiderRefactor.SpiderConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/admin")
public class SpiderController {
  @RequestMapping("spider.html")
  public String showSpider(){ return "/admin/spider"; }

  @ResponseBody
  @RequestMapping("editSpider")
  public String editSpider(HttpServletRequest request){
    String spiderName = request.getParameter("spiderName");//执行爬虫名
    String savePath = request.getParameter("savePath");//储存路径
    //下面这几项必须不能为空
    int initialDelay = Integer.parseInt(request.getParameter("initialDelay"));//多久后执行
    int period = Integer.parseInt(request.getParameter("period"));//执行周期
    TimeUnit timeUnit = TimeUnit.valueOf(request.getParameter("timeUnit"));//执行时间间隔单位

    System.out.println(period);
    System.out.println(timeUnit.name());
    List<SpiderConfig> spiderConfigList = new ArrayList<>();

    if(spiderName.indexOf(" ") != -1 ){
      String[] spiderNames = spiderName.split(" ");
      for(String val : spiderNames){
        SpiderConfig spiderConfig = new SpiderConfig();
        spiderConfig.setSpiderName(val);
        spiderConfigList.add(spiderConfig);
      }
    }else{
      SpiderConfig spiderConfig = new SpiderConfig();
      spiderConfig.setSpiderName(spiderName);
      spiderConfigList.add(spiderConfig);
    }

 //   SpiderConfig spiderConfig = new SpiderConfig();
 //   System.out.println(spiderName);
 //   System.out.println("ss");
    JSONObject result = new JSONObject();
    //一番设置传参
    int flag = 0;
    for(SpiderConfig spiderConfig : spiderConfigList){
      Thread thread = new Thread(){
        public void run(){
          Main main = new Main();
          main.run(spiderConfig);
        }
      };
      thread.start();
      //线程运行起了
      if(thread.isAlive() == true){
        flag++;
        if(flag == spiderConfigList.size()){
          result.put("state","success");
        }else{
          result.put("state","error");
        }
      }else{
        result.put("state","error");
      }
    }
//    Thread thread = new Thread(){
//      public void run(){
//        Main main = new Main();
//        main.run(spiderConfig);
//      }
//    };
//    thread.start();


    return result.toJSONString();
  //  return
  }
}

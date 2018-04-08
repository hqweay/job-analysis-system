package com.swpu.jobanalysissystem.controller.admin;


import com.alibaba.fastjson.JSONObject;


import com.github.pagehelper.PageHelper;
import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.entity.JobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class JobController {
  @Autowired
  private JobInfoMapper jobInfoMapper;

  @RequestMapping(value = {
          "job.html"
  })
  public String showAdminJob(){

    return "/admin/job";
  }

  @ResponseBody
  @RequestMapping(value="jobPageInfo",produces="html/text;charset=UTF-8")
  public String pageInfo(@RequestParam int pageNumber, int pageSize, HttpServletResponse response) {
    response.setContentType("text/json");
    response.setCharacterEncoding("utf-8");
    PageHelper.startPage(pageNumber,pageSize);
    List<JobInfo> pageInfo = jobInfoMapper.selectJobInfo();
    int total = jobInfoMapper.getCount();
    JSONObject result = new JSONObject();
    result.put("rows",pageInfo);
    result.put("total",total);
    //开启服务器端分页必须得要key
  //  System.out.println(result.toJSONString());
    return result.toJSONString();
  }

  @ResponseBody
  @RequestMapping("addJob")
  public String addJob(@Valid JobInfo jobInfo){
    int id = jobInfoMapper.getCount() + 1;
    jobInfo.setId(id);
    int flag = jobInfoMapper.addJob(jobInfo);
    JSONObject result = new JSONObject();
    if(flag > 0){
      result.put("state","success");
    }
    return result.toJSONString();
  }

  @ResponseBody
  @RequestMapping("editJob")
  public String editJob(@Valid JobInfo jobInfo){
    int flag = jobInfoMapper.editJob(jobInfo);
    JSONObject result = new JSONObject();
    if(flag > 0){
      result.put("state","success");
    }
    return result.toJSONString();
  }

  @ResponseBody
  @RequestMapping("deleteJob")
  public String deleteJob(HttpServletRequest request){
    String[] list=request.getParameterValues("ids");
    for (int i = 0; i < list.length; i++) {
      String id = list[i];
      jobInfoMapper.deleteJob(id);
    }
    JSONObject result = new JSONObject();
    result.put("state", "success");
    return result.toJSONString();
  }


}

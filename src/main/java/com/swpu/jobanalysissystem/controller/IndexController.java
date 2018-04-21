package com.swpu.jobanalysissystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.entity.JobInfo;
import com.swpu.jobanalysissystem.entity.User;
import com.swpu.jobanalysissystem.pojo.Login;
import com.swpu.jobanalysissystem.service.IndexService;
import com.swpu.jobanalysissystem.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

//有大问题额
@Controller
public class IndexController {
  Login user;
  @Autowired
  private JobInfoMapper jobInfoMapper;
  @Autowired
  private SecurityService securityService;
  @Autowired
  private IndexService indexService;

 // @Secured("user")
  @RequestMapping(value = {
          "/","/index.html"
  })
  public String showIndex(Model model){
    if(securityService.hasRole("ROLE_USER") ||securityService.hasRole("ROLE_ADMIN") ){
     //定制首页
      model = indexService.showVIPIndex(model);
    }else{
      //显示基本信息的首页
      model = indexService.showBasicIndex(model);
    }
    return "index";
  }

  @ResponseBody
  @RequestMapping(value="/job",produces="html/text;charset=UTF-8")
  public String getJobs(@RequestParam int pageNumber, int pageSize, HttpServletResponse response) {
    PageHelper.startPage(pageNumber,pageSize);
    List<JobInfo> pageInfo = jobInfoMapper.selectJobInfo();
    int total = jobInfoMapper.getCount();
    JSONObject result = new JSONObject();
    result.put("rows",pageInfo);
    result.put("total",total);

    return result.toJSONString();
  }
  @ResponseBody
  @RequestMapping(value="/searchJob",produces="html/text;charset=UTF-8")
  public String getJobs(@RequestParam int pageNumber, int pageSize, String txt) {
    PageHelper.startPage(pageNumber,pageSize);
    List<JobInfo> pageInfo = jobInfoMapper.selectJobInfoByTxt(txt);
    int total = jobInfoMapper.selectJobInfoByTxtCount(txt);

   // System.out.println(total);

    JSONObject result = new JSONObject();
    result.put("rows",pageInfo);
    result.put("total",total);

    return result.toJSONString();
  }
}

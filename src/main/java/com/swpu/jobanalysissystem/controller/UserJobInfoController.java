package com.swpu.jobanalysissystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.swpu.jobanalysissystem.dao.CompanyMapper;
import com.swpu.jobanalysissystem.dao.JobGroupRelationMapper;
import com.swpu.jobanalysissystem.dao.JobImageMapper;
import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.entity.JobImage;
import com.swpu.jobanalysissystem.pojo.Company;
import com.swpu.jobanalysissystem.pojo.JobAnalysis;
import com.swpu.jobanalysissystem.entity.JobInfo;
import com.swpu.jobanalysissystem.until.SqlQueryRecommedStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import static com.swpu.jobanalysissystem.until.Regression_analysis.getPredict;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserJobInfoController {

  @Autowired
  private SqlQueryRecommedStrategy sqlQueryRecommedStrategy;
  @Autowired
  private JobInfoMapper jobInfoMapper;
  @Autowired
  private JobGroupRelationMapper jobGroupRelationMapper;
  @Autowired
  private JobImageMapper jobImageMapper;
  @Autowired
  private CompanyMapper companyMapper;
  @RequestMapping("/user-job-information.html")
  public String getLikes(@ModelAttribute("jobAnalysis") JobAnalysis jobAnalysis, Model model) {
    Integer jobId;
    //对jobAnalysis 处理 空值处理
    if(jobAnalysis.getXueli() == ""){
      jobAnalysis.setXueli("本科");
    }
    if(jobAnalysis.getQiuzhidi() == "") {
      jobAnalysis.setQiuzhidi("北京");
    }
    if(jobAnalysis.getGongzuonianxian() == ""){
      jobAnalysis.setGongzuonianxian("1");
    }
    if(jobAnalysis.getCompany_prop() == ""){
      jobAnalysis.setCompany_prop("国企");
    }
    //由技能label 来找group ID
    if(jobAnalysis.getJineng().indexOf(" ") != -1 ){
      String[] abilities = jobAnalysis.getJineng().split(" ");
      //由 ability 来找 group ID
      jobId = jobImageMapper.getJobIdByLabel(abilities[0]);
    }else{
      jobId = jobImageMapper.getJobIdByLabel(jobAnalysis.getJineng());
    }
    if(jobId == null){
      //如果没查到的话
      jobId = 25;
    }
    int groupId = jobGroupRelationMapper.getJobGroupId(jobId);

    try{
      String salary = getPredict(jobAnalysis.getXueli(), jobAnalysis.getQiuzhidi(), jobAnalysis.getGongzuonianxian(), String.valueOf(groupId), jobAnalysis.getCompany_prop());
      //保留两位小数
    //  DecimalFormat format = new DecimalFormat("#.00");
    //  String str = format.format(salary);
    //  salary = Double.parseDouble(str);

    //  jobAnalysis.setMin_salary(String.valueOf(salary) + " 元");
      jobAnalysis.setMin_salary(salary + " 元");
      jobAnalysis.setGongzuonianxian(jobAnalysis.getGongzuonianxian() + "年");
    }catch (Exception e){
      System.out.println("出粗了");
    }

    //把相关信息传到前端去，前端用 ajax 来显示 Jobs
    String result = JSON.toJSONString(jobAnalysis);
    model.addAttribute("jobAnalysis", result);
    return "user-job-information";
  }

  @ResponseBody
  @RequestMapping(value="/getJobsForUser", method = POST)
  public String getJobsForUser( @RequestParam int pageNumber, int pageSize, String jobAnalysis){
    //字符转 json
    JSONObject jobAnalysisStr = JSONObject.parseObject(jobAnalysis);
    //json 转对象
    JobAnalysis jobAnalysisA = (JobAnalysis) JSONObject.toJavaObject(jobAnalysisStr, JobAnalysis.class);

    PageHelper.startPage(pageNumber,pageSize);
    List<JobInfo> jobs = sqlQueryRecommedStrategy.getRecommendJobIds(jobAnalysisA);
    int count = jobInfoMapper.getRecommenedJobsCountBySqlQuery(jobAnalysisA);

    JSONObject result = new JSONObject();
    result.put("rows",jobs);
    result.put("total",count);
    return result.toJSONString();
  }
  @ResponseBody
  @RequestMapping(value="/getCompanyForUser", method = POST)
  public String getCompanyForUser( @RequestParam int pageNumber, int pageSize, String jobAnalysis){
    //字符转 json
    JSONObject jobAnalysisStr = JSONObject.parseObject(jobAnalysis);
    //json 转对象
    JobAnalysis jobAnalysisA = (JobAnalysis) JSONObject.toJavaObject(jobAnalysisStr, JobAnalysis.class);

    PageHelper.startPage(pageNumber,pageSize);


    PageHelper.startPage(pageNumber,pageSize);
    List<Company> companies = companyMapper.getCompanyForUser(jobAnalysisA);
    Integer count = companyMapper.getCompanyForUserCount(jobAnalysisA);
    JSONObject result = new JSONObject();
    result.put("rows",companies);
    result.put("total",count);
    return result.toJSONString();
  }
}

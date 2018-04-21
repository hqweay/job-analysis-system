package com.swpu.jobanalysissystem.controller.dataShow;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.swpu.jobanalysissystem.dao.CompanyMapper;
import com.swpu.jobanalysissystem.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CompanyController {

  @Autowired
  private CompanyMapper companyMapper;


  @RequestMapping("dataShow/company.html")
  public String company(Model model){

    return "dataShow/company";
  }

  @ResponseBody
  @RequestMapping("/dataShow/getCompanies")
  public String getCompanies(@RequestParam int pageNumber, int pageSize, String txt){
    PageHelper.startPage(pageNumber,pageSize);
    List<Company> companies = companyMapper.getCompanies(txt);
    Integer count = companyMapper.getCompanyCount(txt);
    JSONObject result = new JSONObject();
    result.put("rows",companies);
    result.put("total",count);
    return result.toJSONString();
  }

}

package com.swpu.jobanalysissystem.controller.dataShow;


import com.swpu.jobanalysissystem.dao.DataShowMapper;
import com.swpu.jobanalysissystem.pojo.dataShow.JobPlaceCountSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class OtherDataController {
  @Autowired
  private DataShowMapper dataShowMapper;

  @RequestMapping("dataShow/other-show.html")
  public String otherShow(Model model){
    List<JobPlaceCountSalary> jobPlaceCountSalaryOrderByCountList = dataShowMapper.getJobsByPlaceCountSalaryOrderByCountTopTen();
    List<JobPlaceCountSalary> jobPlaceCountSalaryOrderBySalaryList = dataShowMapper.getJobsByPlaceCountSalaryOrderByCountTopTen();
    model.addAttribute("jobPlaceCountSalaryOrderByCountList", jobPlaceCountSalaryOrderByCountList);
    model.addAttribute("jobPlaceCountSalaryOrderBySalaryList", jobPlaceCountSalaryOrderBySalaryList);
    return "dataShow/other-show";
  }

}

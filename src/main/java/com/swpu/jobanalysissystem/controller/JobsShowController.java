package com.swpu.jobanalysissystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobsShowController {

@RequestMapping("jobs-show.html")
  public String jobsShow(){


  return "jobs-show";
}
}

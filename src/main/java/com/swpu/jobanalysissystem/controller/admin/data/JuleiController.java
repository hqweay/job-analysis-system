package com.swpu.jobanalysissystem.controller.admin.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JuleiController {
  @RequestMapping("admin/dataWajue/julei.html")
  public String julei(){
    return "/admin/dataWajue/julei";
  }
}

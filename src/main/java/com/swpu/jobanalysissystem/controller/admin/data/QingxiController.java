package com.swpu.jobanalysissystem.controller.admin.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QingxiController {
  @RequestMapping("admin/dataQingXi/qingxi.html")
  public String huiGuifenxi(){
    return "/admin/dataQingXi/qingxi";
  }
}

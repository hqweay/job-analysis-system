package com.swpu.jobanalysissystem.controller.admin.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HuiguifenxiController {

  @RequestMapping("admin/dataWajue/huiguifenxi.html")
  public String huiGuifenxi(){
    return "/admin/dataWajue/huiguifenxi";
  }
}

package com.swpu.jobanalysissystem.controller.admin.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SqlController {
  @RequestMapping("admin/SQL.html")
  public String sql(){
    return "/admin/SQL";
  }
}

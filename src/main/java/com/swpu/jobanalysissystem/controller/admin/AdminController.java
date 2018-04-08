package com.swpu.jobanalysissystem.controller.admin;

import com.swpu.jobanalysissystem.pojo.Login;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
  @RequestMapping(value = {
          "admin",  "admin/index.html"
  })
  public String showAdmin(){
    Login userLogin = (Login) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
    System.out.println(userLogin.getAuthorities());
    return "/admin/index";
  }


  @RequestMapping(value = {
          "admin/login.html"
  })
  public String ddd(){

    return "/admin/login";
  }




}

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
    //设定了权限要把这里清除注释
      Login userLogin = (Login) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
    System.out.println(userLogin.getAuthorities());
    return "/admin/index";
  }

  @RequestMapping(value = {
          "admin/login.html"
  })
  public String login(){
    return "/admin/login";
  }

  @RequestMapping(value = {
          "admin/about.html"
  })
  public String about(){
    return "/admin/about";
  }


}

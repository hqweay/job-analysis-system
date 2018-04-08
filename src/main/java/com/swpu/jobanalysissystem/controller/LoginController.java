package com.swpu.jobanalysissystem.controller;


import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {
  @Autowired
  private UserMapper userMapper;

  @RequestMapping(value = { "/login.html"})
  public String showLogin(){
    return "login";
  }
}

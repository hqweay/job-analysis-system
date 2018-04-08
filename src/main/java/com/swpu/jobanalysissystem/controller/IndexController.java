package com.swpu.jobanalysissystem.controller;

import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.entity.User;
import com.swpu.jobanalysissystem.pojo.Login;
import com.swpu.jobanalysissystem.service.IndexService;
import com.swpu.jobanalysissystem.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//有大问题额
@Controller
public class IndexController {
  Login user;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private SecurityService securityService;
  @Autowired
  private IndexService indexService;

 // @Secured("user")
  @RequestMapping(value = {
          "/","/index.html"
  })
  public String showIndex(Model model){
    if(securityService.hasRole("ROLE_USER") ||securityService.hasRole("ROLE_ADMIN") ){
     //定制首页
      model = indexService.showVIPIndex(model);
    }else{
      //显示基本信息的首页
      model = indexService.showBasicIndex(model);
    }
    return "index";
  }
}

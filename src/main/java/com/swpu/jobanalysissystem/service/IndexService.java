package com.swpu.jobanalysissystem.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import java.util.List;
import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.entity.JobInfo;
import com.swpu.jobanalysissystem.entity.User;
import com.swpu.jobanalysissystem.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class IndexService {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private JobInfoMapper jobInfoMapper;


  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
  public User getUser(){
    Login userLogin = (Login) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
    User user = userMapper.selectByUsernameUser(userLogin.getUsername());


    return user;
  }

  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
  public Model showVIPIndex(Model model){
    User user = getUser();
    model.addAttribute("role",user);
    //得到推荐jobs
  //  PageHelper.startPage(3,5);
  //  List<JobInfo> jobInfoList = jobInfoMapper.selectJobInfo();
  //  model.addAttribute("jobs", jobInfoList);
    return model;
  }

  public Model showBasicIndex( Model model){
  //  PageHelper.startPage(1,5);
  //  List<JobInfo> jobInfoList = jobInfoMapper.selectJobInfo();
  //  String jobs = JSON.toJSONString(jobInfoList);
       model.addAttribute("role",null);
  //  model.addAttribute("jobs", jobInfoList);
    //展示基本首页各种情况
    return model;
  }
}

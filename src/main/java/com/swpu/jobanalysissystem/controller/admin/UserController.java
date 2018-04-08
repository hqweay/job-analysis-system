package com.swpu.jobanalysissystem.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
  @Autowired
  private UserMapper userMapper;
  @RequestMapping(value = {
          "user.html"
  })
  public String showAdminUser(){
    return "/admin/user";
  }

  @ResponseBody
  @RequestMapping(value="userPageInfo",produces="html/text;charset=UTF-8")
  public String pageInfo(@RequestParam int pageNumber, int pageSize, HttpServletResponse response) {
    response.setContentType("text/json");
    response.setCharacterEncoding("utf-8");
    PageHelper.startPage(pageNumber,pageSize);
    List<User> pageInfo = userMapper.selectUser();
    int total = userMapper.getCount();
    JSONObject result = new JSONObject();
    result.put("rows",pageInfo);
    result.put("total",total);
    return result.toJSONString();
  }

  @ResponseBody
  @RequestMapping("addUser")
  public String addJob(@Valid User user){
    System.out.println("收到了吗");
    int flag = userMapper.addUser(user);
    JSONObject result = new JSONObject();
    if(flag > 0){
      result.put("state","success");
    }
    return result.toJSONString();
  }

  @ResponseBody
  @RequestMapping("editUser")
  public String editJob(@Valid User user){
    int flag = userMapper.editUser(user);
    JSONObject result = new JSONObject();
    if(flag > 0){
      result.put("state","success");
    }
    return result.toJSONString();
  }

  @ResponseBody
  @RequestMapping("deleteUser")
  public String deleteJob(HttpServletRequest request){
    String[] list=request.getParameterValues("ids");
    for (int i = 0; i < list.length; i++) {
      String id = list[i];
      userMapper.deleteUser(id);
    }
    JSONObject result = new JSONObject();
    result.put("state", "success");
    return result.toJSONString();
  }
}

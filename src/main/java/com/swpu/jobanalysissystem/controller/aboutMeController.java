package com.swpu.jobanalysissystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class aboutMeController {
  @Autowired
  private UserMapper userMapper;
  @RequestMapping("aboutMe")
  public String showAboutMe(int id, Model model){
    User user = userMapper.selectById(id);
    String userJson = JSON.toJSONString(user);
    model.addAttribute("user", userJson);
    return "aboutMe";
  }
  @ResponseBody
  @RequestMapping("editUser")
  public String editUser(@Valid User user){
    int flag = userMapper.editUser(user);
    JSONObject result = new JSONObject();
    if(flag > 0){
      result.put("state","success");
    }
    return result.toJSONString();
  }
}

package com.swpu.jobanalysissystem.service;

import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


//前面的配置改了后这里必须要注入
//返回一个对象用于判断登录

@Component
public class MyUserDetailsService implements UserDetailsService {


  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Login user = userMapper.selectByUsernameLogin(username);

    if( user == null ){
      throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
    }

    //root的话设置为admin哦哦哦
    if(user.getUsername().equals("root")){
      user.setRole("ROLE_ADMIN");
    }else{
      user.setRole("ROLE_USER");
    }


    return user;
  }
}

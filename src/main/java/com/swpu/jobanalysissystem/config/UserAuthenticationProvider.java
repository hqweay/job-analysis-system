package com.swpu.jobanalysissystem.config;

import com.swpu.jobanalysissystem.pojo.Login;
import com.swpu.jobanalysissystem.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//设置为bean
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

  //需要这个吗？试试先
  @Autowired
  private MyUserDetailsService myUserDetailsService;
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

    //从数据库读取的
    Login userDetails = (Login) myUserDetailsService.loadUserByUsername(token.getName());


    if (userDetails == null) {
      throw new UsernameNotFoundException("找不到该用户");
    }
    if(!userDetails.getPassword().equals(token.getCredentials().toString()))
    {
      throw new BadCredentialsException("密码错误");
    }

    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    //狗日的下面这个是调用自带的密码匹配？
    //return null; //如果后续要有验证密码的provider，这里需要直接返回null
  }


  @Override
  public boolean supports(Class<?> authentication) {
    return false;
  }
}

package com.swpu.jobanalysissystem.config;

import org.springframework.security.crypto.password.PasswordEncoder;



/*
* 有些版本不要求这样子做,所以就不会有该问题的发生,
*我现在用的是5.0版本,强制要求提供一个,所以我们就给一个PasswordEncoder给他.
*我们也可以使用Spring自带的PasswordEncoder.为了方便起见,我们以明文形式存密码在后台,故使用自定义PasswordEncoder
*/
public class MyPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence arg0) {
    return arg0.toString();
  }

  @Override
  public boolean matches(CharSequence arg0, String arg1) {
    return arg1.equals(arg0.toString());
  }

}

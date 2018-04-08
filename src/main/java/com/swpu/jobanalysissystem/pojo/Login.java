package com.swpu.jobanalysissystem.pojo;

import com.sun.istack.NotNull;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


//继承后面的接口才能实现那边的操作额....
public class Login implements UserDetails, Serializable {
  @NotNull
  private String name;
  @NotNull
  private String email;
  @NotNull
  private String password;
  private String role;

  public void setName(String name) {
    this.name = name;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public String getRole() {
    return role;
  }
  public String getPassword() {
    return password;
  }
  public String getUsername() {
    return name;
  }
  public boolean isAccountNonExpired() {
    return true;
  }
  public boolean isAccountNonLocked() {
    return true;
  }
  public boolean isCredentialsNonExpired() {
    return true;
  }
  public boolean isEnabled() {
    return true;
  }

  //重写一下
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
    ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    //通过用户的Role 来设置权限
    GrantedAuthority au = new SimpleGrantedAuthority(role);
    list.add(au);
    return list;
  }
}

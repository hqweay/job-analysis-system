package com.swpu.jobanalysissystem.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {
  public boolean hasRole(String role) {
    // get security context from thread local
    SecurityContext context = SecurityContextHolder.getContext();
    if (context == null)
      return false;
    Authentication authentication = context.getAuthentication();
    if (authentication == null)
      return false;

    for (GrantedAuthority auth : authentication.getAuthorities()) {
      if (role.equals(auth.getAuthority()))
        return true;
    }
    return false;
  }
}

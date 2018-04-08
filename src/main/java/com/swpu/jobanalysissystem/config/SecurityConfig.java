package com.swpu.jobanalysissystem.config;

import com.swpu.jobanalysissystem.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;

import javax.servlet.http.HttpServletRequest;


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  //用UserDetail...也阔以 怎么了
  @Autowired
  private MyUserDetailsService userService;

  //@Autowired
  //private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

  @Autowired
  private AuthenticationProvider userAuthenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/abouMe")
            .authenticated()
         //   .antMatchers("/job-analysis.html")
            .antMatchers("/admin")
            //具备权限  hasRole 是具备角色
            //authenticated 要求用户必须已经登录
            .hasAuthority("ROLE_ADMIN")
            //后面如果再用antMatchers 意味着对上面的恶fiter补充 使之更加具体，
            //想使之平行，可以把两个路径放在一个matcher里面，或者跟上and




            .and()
    .formLogin()
                //下面这里的参数是登录页面的地址
            //配置这个后冲突与Springmvc了
            //妈的删了out文件夹才ojbk
                  .loginPage("/login.html").permitAll()       //这里程序默认路径就是登陆页面，允许所有人进行登陆
            //默认就是/login，改了后前端也能看到变化  （默认登录页面）的前端是根据这个渲染的
            //必须有这个
             .loginProcessingUrl("/login")              //登陆提交的处理url意思是让这个url（通常是action）来处理表单
      //        .failureUrl("/login?error=true")
            // .failureForwardUrl("/?error=true");
            //这里填写前端form里的name
            .usernameParameter("username")
            .passwordParameter("password")
       //     .successForwardUrl("/")
       //就是这里     .defaultSuccessUrl("/")
            .and()
            .headers().frameOptions().disable()
            .and()
   .logout()
              .logoutSuccessUrl("/")
            .and()
            .csrf()
            .disable();

  }

  //使用自己的验证方式
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(userAuthenticationProvider);//重点
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    //这里后面跟了两个方法哦哦哦
    auth
            .userDetailsService(this.userService)
            .passwordEncoder(new MyPasswordEncoder());      //在此处应用自定义PasswordEncoder

    //        authenticationProvider(userAuthenticationProvider)
            //userDetailsService(userService);
    //userService通过用户名找到user对象，再把该对象传入下面的方法，人家会比较前台传来密码对不对
    //auth.userDetailsService();
  // .inMemoryAuthentication()

   //         .withUser("user")
   //        .password("lll")
    //        .roles("ss");
  }



  //哦 这个是安全方言的注册

//下面好像没什么用 不知道干嘛的 留一手先
//  @Bean
//  public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
//    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//    templateEngine.setTemplateResolver(templateResolver);
//    templateEngine.addDialect(new SpringStandardDialect());
//    return templateEngine;
//
//  }
}

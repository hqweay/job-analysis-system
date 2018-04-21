package com.swpu.jobanalysissystem.controller;

import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.dao.RecommendJobImageMapper;
import com.swpu.jobanalysissystem.dao.UserMapper;
import com.swpu.jobanalysissystem.entity.User;
import com.swpu.jobanalysissystem.pojo.Login;
import com.swpu.jobanalysissystem.service.SecurityService;
import com.swpu.jobanalysissystem.until.ContentRecommedStrategy;
import com.swpu.jobanalysissystem.until.SqlQueryRecommedStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lenovo on 2018/3/7.
 */
@Controller
public class JobAnalysisController {
    @Autowired
    public RecommendJobImageMapper recommendJobImageMapper;
    @Autowired
    public ContentRecommedStrategy contentRecommedStrategy;
    @Autowired
    public JobInfoMapper jobInfoMapper;
    @Autowired
    public SqlQueryRecommedStrategy sqlQueryRecommedStrategy;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = {
            "/job-analysis.html"
    })
    public String showJobAnalysis(Model model){
        //若已经登陆啦
        if(securityService.hasRole("ROLE_USER") ||securityService.hasRole("ROLE_ADMIN")){
            Login userLogin = (Login) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            User user = userMapper.selectByUsernameUser(userLogin.getUsername());
            model.addAttribute("user", user);
        }

        return "job-analysis";
    }






}

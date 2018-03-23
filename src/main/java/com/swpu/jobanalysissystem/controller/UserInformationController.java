package com.swpu.jobanalysissystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2018/3/7.
 */
@Controller
public class UserInformationController {
    @RequestMapping(value = {
            "/user-information.html"
    })
    public String jobAnalysis(){
        return "user-information";
    }
}

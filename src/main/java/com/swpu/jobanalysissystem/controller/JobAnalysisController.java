package com.swpu.jobanalysissystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by lenovo on 2018/3/7.
 */
@Controller
public class JobAnalysisController {
    @RequestMapping(value = {
            "/job-analysis.html"
    })
    public String jobAnalysis(){
        return "job-analysis";
    }
    @RequestMapping(value = "/job-analysis", method = POST)
    public String getUserInformation(){
        return "user-information";
    }
}

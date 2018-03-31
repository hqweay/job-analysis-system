package com.swpu.jobanalysissystem.controller;

import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.dao.RecommendJobImageMapper;
import com.swpu.jobanalysissystem.entity.JobInfo;
import com.swpu.jobanalysissystem.entity.JobAnalysis;
import com.swpu.jobanalysissystem.entity.RecommedJobImage;
import com.swpu.jobanalysissystem.until.ContentRecommedStrategy;
import com.swpu.jobanalysissystem.until.SqlQueryRecommedStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = {
            "/job-analysis.html"
    })
    public String jobAnalysis(){
        return "job-analysis";
    }

    @RequestMapping(value = "/job-analysis.html", method = POST)
    public String getUserInformation(@ModelAttribute("jobAnalysis") JobAnalysis jobAnalysis, Model model){
//        ArrayList<Integer> jobIds = new ArrayList<Integer>();
//        //得到list
//        List<RecommedJobImage> recommedJobImages = recommendJobImageMapper.getRecommenedJobImages();
//        //web前端传来的user信息封装
//        HashMap<String,Double> user = new HashMap<>();
//        user.put(jobAnalysis.getXueli(),1.0);
//
//        user.put(jobAnalysis.getQiuzhidi(),5.0);
//        user.put(jobAnalysis.getGongzuonianxian(),1.0);
//        user.put(jobAnalysis.getJineng(),1.0);
//
//        //获得job的id集合并格式化
//        jobIds = contentRecommedStrategy.getRecommendJobIds(user);
//        //格式化一下
//        String ids = jobIds.toString().replace("[", "").replace("]","");
//        //通过格式化的id集合找到job集合
//        List<JobInfo> jobs = jobInfoMapper.getRecommenedJobs(ids);

        List<JobInfo> jobs;
        jobs = sqlQueryRecommedStrategy.getRecommendJobIds(jobAnalysis);
        //jobs = recommendJobImageMapper.getRecommenedJobsBySqlQuery(jobAnalysis);

        System.out.println(jobs.get(0).getJob_desc());

        model.addAttribute("jobs", jobs);
        return "user-job-information";

    }
}

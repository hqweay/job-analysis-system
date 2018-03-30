package com.swpu.jobanalysissystem.controller;

import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.dao.RecommendJobImageMapper;
import com.swpu.jobanalysissystem.entity.JobInfo;
import com.swpu.jobanalysissystem.entity.RecommedJobImage;
import com.swpu.jobanalysissystem.entity.SearchJobBean;
import com.swpu.jobanalysissystem.until.ContendRecommedStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
    public ContendRecommedStrategy contendRecommedStrategy;

    @Autowired
    public JobInfoMapper jobInfoMapper;

    @RequestMapping(value = {
            "/job-analysis.html"
    })
    public String jobAnalysis(){
        return "job-analysis";
    }

    @RequestMapping(value = "/job-analysis.html", method = POST)
    public String getUserInformation(@ModelAttribute("searchJobBean") SearchJobBean searchJobBean, Model model){
        ArrayList<Integer> jobIds = new ArrayList<Integer>();
        List<RecommedJobImage> recommedJobImages = recommendJobImageMapper.getRecommenedJobImages();
        System.out.println(recommedJobImages);

        HashMap<String,Double> user = new HashMap<>();

        System.out.println(searchJobBean.getGongzuonianxian());
        System.out.println(searchJobBean.getJineng());

        user.put(searchJobBean.getXueli(),1.0);
        user.put(searchJobBean.getZhuanye(),1.0);
        user.put(searchJobBean.getXuexiao(),1.0);
        user.put(searchJobBean.getQiuzhidi(),5.0);

        user.put(searchJobBean.getGongzuonianxian(),1.0);
        user.put(searchJobBean.getJineng(),1.0);
        user.put(searchJobBean.getJingli(),1.0);



        jobIds = contendRecommedStrategy.glgorithm(user);
        String ids = jobIds.toString().replace("[", "").replace("]","");

        List<JobInfo> jobs = jobInfoMapper.getRecommenedJobs(ids);

        System.out.println("以下是推荐哦");
        System.out.println(jobs);
        model.addAttribute("jobs", jobs);

        return "user-information";

    }
}

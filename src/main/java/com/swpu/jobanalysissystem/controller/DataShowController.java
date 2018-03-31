package com.swpu.jobanalysissystem.controller;

import com.alibaba.fastjson.JSON;
import com.swpu.jobanalysissystem.dao.*;

import com.swpu.jobanalysissystem.entity.*;

import com.swpu.jobanalysissystem.until.ContentRecommedStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by lenovo on 2018/3/7.
 */
@Controller
public class DataShowController {

    @Autowired
    private PlaceSalaryMapper placeSalaryMapper;

    @Autowired
    private XueliSalaryMapper xueliSalaryMapper;

    @Autowired
    private ExperienceSalaryMapper experienceSalaryMapper;

    @Autowired
    private CompanySalaryMapper companySalaryMapper;

    @Autowired
    private JobImageMapper jobImageMapper;

    @Autowired
    public RecommendJobImageMapper recommendJobImageMapper;

    @Autowired
    public ContentRecommedStrategy contentRecommedStrategy;

    //访问data-show页面
    @RequestMapping(value = {
            "/data-show.html"
    })
    public String showData(Model model){
        //获得页面相关内容
//        List<PlaceSalary> placeSalaries = placeSalaryMapper.getAllPlaceSalary();//Palece_Salary
        List<PlaceSalary> placeSalaries = placeSalaryMapper.getPlaceSalaryTopTen();//Palece_Salary
        List<XueliSalary> xueliSalaries = xueliSalaryMapper.getAllXueliSalary();//XUeli_Salary
        List<ExperienceSalary> experienceSalaries = experienceSalaryMapper.getAllExperienceSalary();//Experience_Salary
        List<CompanySalary> companySalaries = companySalaryMapper.getAllCompanySalary();//Companyprop_Salary
        List<JobImage> jobImages = jobImageMapper.getAllJobImage();//job_image

        //转json
        String strPlaceSalaries = JSON.toJSONString(placeSalaries);
        String strXueliSalaries = JSON.toJSONString(xueliSalaries);
        String strExperienceSalaries = JSON.toJSONString(experienceSalaries);
        String strCompanySalaries = JSON.toJSONString(companySalaries);
        String strJobImages = JSON.toJSONString(jobImages);

        //System.out.println(jobImages);
        //System.out.println(strJobImages);
        //返回
        model.addAttribute("placeSalary", strPlaceSalaries);
        model.addAttribute("xueliSalary", strXueliSalaries);
        model.addAttribute("experienceSalary", strExperienceSalaries);
        model.addAttribute("companySalary", strCompanySalaries);
        model.addAttribute("jobImage", strJobImages);






//            List<RecommedJobImage> s = recommendJobImageMapper.getRecommenedJobImages();
//        //System.out.println(s);
//        HashMap<String,Double> user = new HashMap<>();
//        user.put("本科",1.0);
//        user.put("软件工程",1.0);
//        user.put("西南石油大学",1.0);
//        user.put("成都",1.0);
//        user.put("1",1.0);
//        user.put("hadoop",1.0);
//        user.put("参加过大数据比赛",1.0);
//        contendRecommedStrategy.glgorithm(user);

        return "data-show";
    }
}

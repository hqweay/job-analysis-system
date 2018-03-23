package com.swpu.jobanalysissystem.controller;

import com.alibaba.fastjson.JSON;
import com.swpu.jobanalysissystem.dao.CompanySalaryMapper;
import com.swpu.jobanalysissystem.dao.ExperienceSalaryMapper;
import com.swpu.jobanalysissystem.dao.PlaceSalaryMapper;

import com.swpu.jobanalysissystem.dao.XueliSalaryMapper;
import com.swpu.jobanalysissystem.entity.CompanySalary;
import com.swpu.jobanalysissystem.entity.ExperienceSalary;

import com.swpu.jobanalysissystem.entity.PlaceSalary;

import com.swpu.jobanalysissystem.entity.XueliSalary;
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


        //转json
        String strPlaceSalaries = JSON.toJSONString(placeSalaries);
        String strXueliSalaries = JSON.toJSONString(xueliSalaries);
        String strExperienceSalaries = JSON.toJSONString(experienceSalaries);
        String strCompanySalaries = JSON.toJSONString(companySalaries);

        System.out.println(placeSalaries.get(0).getTop_salary());

        //返回
        model.addAttribute("placeSalary", strPlaceSalaries);
        model.addAttribute("xueliSalary", strXueliSalaries);
        model.addAttribute("experienceSalary", strExperienceSalaries);
        model.addAttribute("companySalary", strCompanySalaries);




        return "data-show";
    }
}

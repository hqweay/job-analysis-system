package com.swpu.jobanalysissystem.controller;

import com.alibaba.fastjson.JSON;
import com.swpu.jobanalysissystem.dao.PlaceSalaryMapper;
import com.swpu.jobanalysissystem.entity.PlaceSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

/**
 * Created by lenovo on 2018/3/17.
 */
@Controller
public class Test{

    @Autowired
    private PlaceSalaryMapper placeSalaryMapper;

    @RequestMapping(value= "/test.html", produces = "application/json; charset=utf-8")
    public String jobAnalysis(Model model) {
        List<PlaceSalary> placeSalaries = placeSalaryMapper.getAllPlaceSalary();

        String str = JSON.toJSONString(placeSalaries);
        String strOne = JSON.toJSONString(placeSalaries.get(0));


        model.addAttribute("region", str);
        model.addAttribute("regionOne", strOne);

        System.out.println(str);


        return "test";
    }

    @RequestMapping(value= "/hehe.html", produces = "application/json; charset=utf-8")
    public String Hehe(Model model) {
        List<PlaceSalary> placeSalaries = placeSalaryMapper.getAllPlaceSalary();

        String str = JSON.toJSONString(placeSalaries);
        String strOne = JSON.toJSONString(placeSalaries.get(0));
        model.addAttribute("region", str);
        model.addAttribute("regionOne", strOne);
        System.out.println(str);
        return "hehe";
    }



}

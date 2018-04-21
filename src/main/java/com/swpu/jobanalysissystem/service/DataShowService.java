package com.swpu.jobanalysissystem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.swpu.jobanalysissystem.dao.*;
import com.swpu.jobanalysissystem.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.*;


@Component
public class DataShowService {
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private PlaceSalaryMapper placeSalaryMapper;

    @Autowired
    private XueliSalaryMapper xueliSalaryMapper;

    @Autowired
    private ExperienceSalaryMapper experienceSalaryMapper;

    @Autowired
    private CompanySalaryMapper companySalaryMapper;

    //获取前十岗位能力需求
    public Model getAbilityReqTopTen(Model model){
        List<Map.Entry<String, String>> entryArrayList = this.clearData();
        List list = entryArrayList.subList(0,10);

        model.addAttribute("topTenGroup",list);
        return model;
    }
    //获取所有岗位能力需求
    public Model getAbilityReqAll(Model model){
        List<Map.Entry<String, String>> entryArrayList = this.clearData();
        List list = entryArrayList.subList(0,50);
        model.addAttribute("group",list);
        return model;
    }
    //整理岗位能力需求数据
    private List<Map.Entry<String, String>> clearData(){
        List<Group> groups = groupMapper.selectAllGroup();
        //Set<Map.Entry<String, String>> set = new HashSet<>();
        List<Map.Entry<String, String>> entryArrayList = new ArrayList<>();
//单个提取出来
        for(Group group : groups){
            Map map = JSON.parseObject(group.getKey_word());
            int num = group.getJob_num();
            for (Object mapData : map.entrySet()) {
                Map.Entry<String,String> entry = (Map.Entry<String,String>)mapData;
                double score = Double.parseDouble(entry.getValue()) * num;
                entry.setValue(Double.toString(score));
                entryArrayList.add(entry);
            }
        }
//去重
        for(int i = 0; i < entryArrayList.size(); i++){
            for(int j = 1; j < entryArrayList.size(); j++){
                if(entryArrayList.get(i).getKey() == entryArrayList.get(j).getKey()){
                    double score = Double.parseDouble(entryArrayList.get(i).getValue()) + Double.parseDouble(entryArrayList.get(j).getValue()) ;
                    entryArrayList.get(i).setValue(Double.toString(score));
                    entryArrayList.remove(j);
                }
            }
        }

        //排序 太糟糕了 不排序也阔以现在
//        for(int i = 0; i < entryArrayList.size(); i++){
//            for(int j = 1; j < entryArrayList.size(); j++){
//                double scoreA = Double.parseDouble(entryArrayList.get(i).getValue());
//                double scoreB = Double.parseDouble(entryArrayList.get(j).getValue());
//                if(scoreA > scoreB){
//                    Map.Entry<String, String> entry = entryArrayList.get(j);
//                    entryArrayList.set(j, entryArrayList.get(i));
//                    entryArrayList.set(i, entry);
//                }
//            }
//        }
        return entryArrayList;
    }

    public Model getSalaryData(Model model){
        List<PlaceSalary> placeSalaries = placeSalaryMapper.getPlaceSalaryTopTen();//Palece_Salary
        List<XueliSalary> xueliSalaries = xueliSalaryMapper.getAllXueliSalary();//XUeli_Salary
        List<ExperienceSalary> experienceSalaries = experienceSalaryMapper.getAllExperienceSalary();//Experience_Salary
        List<CompanySalary> companySalaries = companySalaryMapper.getAllCompanySalary();//Companyprop_Salary

        //转json
        String strPlaceSalaries = JSON.toJSONString(placeSalaries);
        String strXueliSalaries = JSON.toJSONString(xueliSalaries);
        String strExperienceSalaries = JSON.toJSONString(experienceSalaries);
        String strCompanySalaries = JSON.toJSONString(companySalaries);

        //返回
        model.addAttribute("placeSalary", strPlaceSalaries);
        model.addAttribute("xueliSalary", strXueliSalaries);
        model.addAttribute("experienceSalary", strExperienceSalaries);
        model.addAttribute("companySalary", strCompanySalaries);
        return model;
    }
}

package com.swpu.jobanalysissystem.controller.dataShow;

import com.alibaba.fastjson.JSON;
import com.swpu.jobanalysissystem.dao.*;

import com.swpu.jobanalysissystem.entity.*;

import com.swpu.jobanalysissystem.service.DataShowService;
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

    @Autowired
    private DataShowService dataShowService;

    //访问data-show页面
    @RequestMapping(value = {
            "/data-show.html"
    })
    public String showData(Model model){
        return "data-show";
    }

    @RequestMapping(value = {
            "dataShow/ability-rep.html"
    })
    public String abilityRep(Model model) {
        //获取所有
        dataShowService.getAbilityReqAll(model);
        return "/dataShow/ability-rep";
    }

    @RequestMapping(value = {
            "dataShow/salary.html"
    })
    public String salary(Model model) {
        //获取工资相关
        dataShowService.getSalaryData(model);
        return "/dataShow/salary";
    }



    @RequestMapping(value = {
            "dataShow/index.html"
    })
    public String index(Model model) {

        //获得页面相关内容
        List<PlaceSalary> placeSalaries = placeSalaryMapper.getPlaceSalaryTopTen();//Palece_Salary
        //转json
        String strPlaceSalaries = JSON.toJSONString(placeSalaries);
        model.addAttribute("placeSalary", strPlaceSalaries);

        //获取前十
        dataShowService.getAbilityReqTopTen(model);



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

        //获取工资相关
//        dataShowService.getSalaryData(model);
        return "/dataShow/index";
    }
}

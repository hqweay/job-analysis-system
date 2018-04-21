package com.swpu.jobanalysissystem.until;

import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.pojo.JobAnalysis;
import com.swpu.jobanalysissystem.entity.JobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SqlQueryRecommedStrategy {

  @Autowired
  private JobInfoMapper jobInfoMapper;

  public  List<JobInfo> getRecommendJobIds(JobAnalysis jobAnalysis){
    //System.out.println(jobAnalysis.toString());
    List<JobInfo> jobInfoList = new ArrayList<>();
    //说明含有空格
    //也就是含有多个标签



    if(jobAnalysis.getJineng().indexOf(" ") != -1 ){

      String[] abilities = jobAnalysis.getJineng().split(" ");
      for(String ability : abilities){
        jobAnalysis.setJineng(ability);
        jobInfoList.addAll(jobInfoMapper.getRecommenedJobsBySqlQuery(jobAnalysis));
      }
    }else{
      jobInfoList.addAll(jobInfoMapper.getRecommenedJobsBySqlQuery(jobAnalysis));
    }


    return jobInfoList;
  }
}

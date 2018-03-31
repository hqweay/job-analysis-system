package com.swpu.jobanalysissystem.until;

import com.swpu.jobanalysissystem.dao.JobInfoMapper;
import com.swpu.jobanalysissystem.dao.RecommendJobImageMapper;
import com.swpu.jobanalysissystem.entity.JobAnalysis;
import com.swpu.jobanalysissystem.entity.JobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SqlQueryRecommedStrategy {

  @Autowired
  private JobInfoMapper jobInfoMapper;

  public  List<JobInfo> getRecommendJobIds(JobAnalysis jobAnalysis){
    //System.out.println(jobAnalysis.toString());
    List<JobInfo> jobInfoList;
    jobInfoList = jobInfoMapper.getRecommenedJobsBySqlQuery(jobAnalysis);
    return jobInfoList;
  }
}

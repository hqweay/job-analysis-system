package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.JobAnalysis;
import com.swpu.jobanalysissystem.entity.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


//获取所有jobInfo对象
@Mapper
public interface JobInfoMapper {
  //由jobids 获取jobs
  @Select("select * from job_info where id in (${jobIds})")
  List<JobInfo> getRecommenedJobs(@Param("jobIds")String jobIds);


  //得修改一下int型的比较方式
  //这个可不能直接简单like呀
  //学历就难得了，也要吗？
  //妈耶 这个麻烦诶
  //最低工资 工作年限
  //妈的工作年限不转数值比较了，里面有中文啊，就这样说不定还要好点
  //最低工资搞一下就ojbk了  这种方式可以吗？我还没转为int啊，但是好像也可以诶

  @Select("select id,job_name,min_salary,top_salary,min_xueli,job_place,company_name,experience,job_url,job_num,job_desc from (select job_id,(GROUP_CONCAT(label SEPARATOR ' ' ) )as label from job_image group by job_id )A join job_info on job_info.id=A.job_id where (label like '%' #{jineng} '%' or job_name like '%' #{jineng} '%') and min_xueli like '%' #{xueli} '%' and experience like '%' #{gongzuonianxian} '%' and job_place like '%' #{qiuzhidi} '%' and min_salary >= #{min_salary} and job_name like '%' #{job_name} '%' limit 10;")
  List<JobInfo> getRecommenedJobsBySqlQuery(JobAnalysis jobAnalysis);
}

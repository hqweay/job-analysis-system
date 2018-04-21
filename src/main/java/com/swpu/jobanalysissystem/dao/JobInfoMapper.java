package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.pojo.JobAnalysis;
import com.swpu.jobanalysissystem.entity.JobInfo;
import org.apache.ibatis.annotations.*;

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

  @Select("select id,job_name,min_salary,top_salary,min_xueli,job_place,company_name,experience,job_url,job_num,job_desc from (select job_id,(GROUP_CONCAT(label SEPARATOR ' ' ) )as label from job_image group by job_id )A join job_info on job_info.id=A.job_id where label like '%' #{jineng} '%' or job_name like '%' #{jineng} '%' or min_xueli = #{xueli} or experience >= #{gongzuonianxian} or job_place  = #{qiuzhidi} or (min_salary >= (#{min_salary} - 2000) and min_salary <= (#{min_salary} + 2000)) or job_name like '%' #{job_name} '%' order by min_salary desc ")
  List<JobInfo> getRecommenedJobsBySqlQuery(JobAnalysis jobAnalysis);


  @Select("select count(id) from (select job_id,(GROUP_CONCAT(label SEPARATOR ' ' ) )as label from job_image group by job_id )A join job_info on job_info.id=A.job_id where label like '%' #{jineng} '%' or job_name like '%' #{jineng} '%' or min_xueli like '%' #{xueli} '%' or experience like '%' #{gongzuonianxian} '%' or job_place like '%' #{qiuzhidi} '%' or  (min_salary >= (#{min_salary} - 2000) and min_salary <= (#{min_salary} + 2000)) or job_name like '%' #{job_name} '%'")
  int getRecommenedJobsCountBySqlQuery(JobAnalysis jobAnalysis);



  //妈的 坑啊 不要加分号 用分页插件的时候是在后面补上
  @Select("select * from job_info")
  List<JobInfo> selectJobInfo();

  @Select("select * from job_info where id = #{txt} or job_name like '%' #{txt} '%' or company_name like '%' #{txt} '%' or job_place like '%' #{txt} '%' ")
  List<JobInfo> selectJobInfoByTxt(String txt);

  @Select("select count(id) from job_info where id = #{txt} or job_name like '%' #{txt} '%' or company_name like '%' #{txt} '%' or job_place like '%' #{txt} '%' ")
  int selectJobInfoByTxtCount(String txt);

  @Select("select count(id) from job_info")
  int getCount();

  @Insert("INSERT INTO job_info (id, job_name,min_salary,top_salary,min_xueli,job_place,company_name,job_url,experience,job_num,job_desc) VALUES ( #{id},#{job_name}," +
          "#{min_salary},#{top_salary},#{min_xueli},#{job_place},#{company_name},#{job_url},#{experience},#{job_num},#{job_desc} )   ")
  int addJob(JobInfo jobInfo);



  @Update("UPDATE job_info SET job_name = #{job_name}, min_salary = #{min_salary}, top_salary = #{top_salary}, min_xueli = #{min_xueli}, job_place = #{job_place}, company_name = #{company_name}, job_url = #{job_url}, experience = #{experience}, job_num = #{job_num}, job_desc = #{job_desc} WHERE id = #{id}")
  int editJob(JobInfo jobInfo);

  @Delete("DELETE FROM job_info WHERE id = ${id}")
  int deleteJob(@Param(value="id") String id);
}

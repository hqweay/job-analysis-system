package com.swpu.jobanalysissystem.dao;


import java.util.List;
import com.swpu.jobanalysissystem.pojo.dataShow.JobPlaceCountSalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataShowMapper {

  @Select("SELECT A.job_place ,count, avg_salary from (SELECT job_place ,count(id) as count from job_info  GROUP BY job_info.job_place) as A INNER JOIN place_salary ON (A.job_place = place_salary.job_place) ORDER BY count DESC limit 10")
  List<JobPlaceCountSalary> getJobsByPlaceCountSalaryOrderByCountTopTen();
  @Select("SELECT A.job_place ,count, avg_salary from (SELECT job_place ,count(id) as count from job_info  GROUP BY job_info.job_place) as A INNER JOIN place_salary ON (A.job_place = place_salary.job_place) ORDER BY avg_salary DESC limit 10")
  List<JobPlaceCountSalary> getJobsByPlaceCountSalaryOrderByAvgSalaryTopTen();
}

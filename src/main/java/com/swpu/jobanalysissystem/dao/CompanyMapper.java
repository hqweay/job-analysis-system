package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.pojo.Company;
import com.swpu.jobanalysissystem.pojo.JobAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper {
  //获取company 信息
  @Select("select company_name,(GROUP_CONCAT(id SEPARATOR ' '))as job_ids, (GROUP_CONCAT(job_name SEPARATOR ' '))as job_names ,(GROUP_CONCAT(min_salary SEPARATOR ' '))as job_min_salaries,(GROUP_CONCAT(job_place SEPARATOR ' '))as job_places,count(job_name) as job_count from job_info  where company_name like '%' #{txt} '%' GROUP BY company_name order by job_min_salaries desc")
  List<Company> getCompanies(String txt);

  @Select("SELECT count(company_name) FROM (select company_name from job_info where company_name like '%' #{txt} '%' GROUP BY company_name ) as count ")
  Integer getCompanyCount(String txt);

  @Select("SELECT * from (select company_name ,(GROUP_CONCAT(id SEPARATOR ' '))as job_ids, (GROUP_CONCAT(job_name " +
          "SEPARATOR ' '))as job_names ,(GROUP_CONCAT(min_salary SEPARATOR ' '))as job_min_salaries,(GROUP_CONCAT" +
          "(job_place SEPARATOR ' '))as job_places,count(job_name) as job_count from job_info GROUP BY company_name) " +
          "as c where job_names LIKE '%' #{job_name} '%' or company_name like '%' #{job_name} '%' or job_places like '%' #{qiuzhidi} '%' or (job_min_salaries >= (#{min_salary}-2000) and job_min_salaries <= (#{min_salary}+2000)) GROUP BY job_min_salaries desc ")
  List<Company> getCompanyForUser(JobAnalysis jobAnalysis);

  @Select("SELECT count(job_count) from (select company_name ,(GROUP_CONCAT(id SEPARATOR ' '))as job_ids, (GROUP_CONCAT(job_name " +
          "SEPARATOR ' '))as job_names ,(GROUP_CONCAT(min_salary SEPARATOR ' '))as job_min_salaries,(GROUP_CONCAT" +
          "(job_place SEPARATOR ' '))as job_places,count(job_name) as job_count from job_info GROUP BY company_name) " +
          "as c where job_names LIKE '%' #{job_name} '%' or company_name like '%' #{job_name} '%' or job_places like '%' #{qiuzhidi} '%' or (job_min_salaries >= (#{min_salary}-2000) and job_min_salaries <= (#{min_salary}+2000))")
  int getCompanyForUserCount(JobAnalysis jobAnalysis);

}

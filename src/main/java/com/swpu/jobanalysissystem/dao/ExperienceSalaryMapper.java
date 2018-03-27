package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.ExperienceSalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ExperienceSalaryMapper {
    @Select("select * from experience_salary order by avg_salary+0 desc")
    List<ExperienceSalary> getAllExperienceSalary();
}
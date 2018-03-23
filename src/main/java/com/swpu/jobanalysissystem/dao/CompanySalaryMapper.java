package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.CompanySalary;
import com.swpu.jobanalysissystem.entity.PlaceSalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CompanySalaryMapper {
    @Select("select * from companyprop_salary order by avg_salary+0 desc")
    List<CompanySalary> getAllCompanySalary();
}
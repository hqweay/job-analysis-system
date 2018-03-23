package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.PlaceSalary;
import com.swpu.jobanalysissystem.entity.XueliSalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface XueliSalaryMapper {
  @Select("select * from xueli_salary order by avg_salary+0 desc")
  List<XueliSalary> getAllXueliSalary();
}
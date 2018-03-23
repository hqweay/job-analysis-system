package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.PlaceSalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlaceSalaryMapper {
    @Select("select * from place_salary")
    List<PlaceSalary> getAllPlaceSalary();

    //妈耶，牛逼诶
    @Select("select * from place_salary order by avg_salary+0 desc limit 10")
    List<PlaceSalary> getPlaceSalaryTopTen();

    @Select("select * from place_salary order by avg_salary+0, min_salary+0 desc limit 10")
    List<PlaceSalary> getPlaceSalaryLowTen();
}
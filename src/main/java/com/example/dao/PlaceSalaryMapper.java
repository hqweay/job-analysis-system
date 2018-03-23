package com.example.dao;

import com.example.entity.PlaceSalary;

public interface PlaceSalaryMapper {
    int deleteByPrimaryKey(String job_place);

    int insert(PlaceSalary record);

    int insertSelective(PlaceSalary record);

    PlaceSalary selectByPrimaryKey(String job_place);

    int updateByPrimaryKeySelective(PlaceSalary record);

    int updateByPrimaryKey(PlaceSalary record);
}
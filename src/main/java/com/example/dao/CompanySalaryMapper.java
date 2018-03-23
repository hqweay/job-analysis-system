package com.example.dao;

import com.example.entity.CompanySalary;

public interface CompanySalaryMapper {
    int insert(CompanySalary record);

    int insertSelective(CompanySalary record);
}
package com.example.dao;

import com.example.entity.ExperienceSalary;

public interface ExperienceSalaryMapper {
    int insert(ExperienceSalary record);

    int insertSelective(ExperienceSalary record);
}
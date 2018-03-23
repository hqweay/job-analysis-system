package com.example.dao;

import com.example.entity.XueliSalary;

public interface XueliSalaryMapper {
    int insert(XueliSalary record);

    int insertSelective(XueliSalary record);
}
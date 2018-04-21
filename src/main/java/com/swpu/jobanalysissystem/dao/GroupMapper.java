package com.swpu.jobanalysissystem.dao;


import com.swpu.jobanalysissystem.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("select * from group_info")
    List<Group> selectAllGroup();



}
package com.swpu.jobanalysissystem.dao;


import com.swpu.jobanalysissystem.entity.JobImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface JobImageMapper {
    @Select("select * from job_image limit 20")
    List<JobImage> getAllJobImage();
}
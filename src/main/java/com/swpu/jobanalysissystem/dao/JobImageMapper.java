package com.swpu.jobanalysissystem.dao;


import com.swpu.jobanalysissystem.entity.JobImage;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface JobImageMapper {
    @Select("select * from job_image limit 20")
    List<JobImage> getAllJobImage();

    @Select("select job_id from job_image where label = #{label} limit 1")
    Integer getJobIdByLabel(String label);
}
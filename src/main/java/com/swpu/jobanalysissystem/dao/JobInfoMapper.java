package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobInfoMapper {
  @Select("select * from job_info where id in (${jobIds})")
  List<JobInfo> getRecommenedJobs(@Param("jobIds")String jobIds);
}

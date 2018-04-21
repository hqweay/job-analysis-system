package com.swpu.jobanalysissystem.dao;

import com.sun.jersey.server.impl.cdi.SyntheticQualifier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JobGroupRelationMapper {

  @Select("select group_id from job_group_relation where job_id = #{jobId}")
  int getJobGroupId(int jobId);


}

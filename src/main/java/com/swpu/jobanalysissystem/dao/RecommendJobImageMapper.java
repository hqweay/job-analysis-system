package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.RecommedJobImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecommendJobImageMapper {
    @Select("select id,label,job_name,min_salary,min_xueli,job_place,experience from (select job_id,(GROUP_CONCAT(label SEPARATOR ' ' ) )as label from job_image group by job_id )A join job_info on job_info.id=A.job_id")
     List<RecommedJobImage> getRecommenedJobImages();
}

package com.swpu.jobanalysissystem.dao;

import com.swpu.jobanalysissystem.entity.JobAnalysis;
import com.swpu.jobanalysissystem.entity.JobInfo;
import com.swpu.jobanalysissystem.entity.RecommedJobImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecommendJobImageMapper {
  /*
  * 获取一个job画像的矩阵
  * 为下一步计算与用户搜索匹配内容的相似度做准备
  * id label job_name min_salary min_xueli experience + company_name
  * 画像的计算用RecommedJobImage来搞 不用JobInfo
  * */
  @Select("select id,label,job_name,min_salary,min_xueli,job_place,experience from (select job_id,(GROUP_CONCAT(label SEPARATOR ' ' ) )as label from job_image group by job_id )A join job_info on job_info.id=A.job_id")
  List<RecommedJobImage> getRecommenedJobImages();



}

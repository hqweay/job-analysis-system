package com.swpu.jobanalysissystem.pojo;

import com.swpu.jobanalysissystem.entity.JobInfo;

import java.util.*;

public class Company {
  private String company_name;
  private String job_ids;
  private String job_names;
  private String job_min_salaries;
  private String job_places;
  private Integer job_count;


  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }

  public String getJob_ids() {
    return job_ids;
  }

  public void setJob_ids(String job_ids) {
    this.job_ids = job_ids;
  }

  public String getJob_names() {
    return job_names;
  }

  public void setJob_names(String job_names) {
    this.job_names = job_names;
  }

  public String getJob_min_salaries() {
    return job_min_salaries;
  }

  public void setJob_min_salaries(String job_min_salaries) {
    this.job_min_salaries = job_min_salaries;
  }

  public String getJob_places() {
    return job_places;
  }

  public void setJob_places(String job_places) {
    this.job_places = job_places;
  }

  public Integer getJob_count() {
    return job_count;
  }

  public void setJob_count(Integer job_count) {
    this.job_count = job_count;
  }
}

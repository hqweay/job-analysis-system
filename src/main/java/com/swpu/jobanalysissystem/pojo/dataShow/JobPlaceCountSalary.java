package com.swpu.jobanalysissystem.pojo.dataShow;

public class JobPlaceCountSalary {
  private String job_place;
  private Integer count;
  private String avg_salary;

  public String getAvg_salary() {
    return avg_salary;
  }

  public void setAvg_salary(String avg_salary) {
    this.avg_salary = avg_salary;
  }

  public String getJob_place() {
    return job_place;
  }

  public void setJob_place(String job_place) {
    this.job_place = job_place;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}

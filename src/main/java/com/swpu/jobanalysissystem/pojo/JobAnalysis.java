package com.swpu.jobanalysissystem.pojo;

/***
 * This is a bean for web search when u want to give some jobs wanted.
 * 搜索表单内容 封装bean
 * @return null
 * */
public class JobAnalysis {
  private String xueli;
  private String xuexiao;
  private String info_major;
  private String qiuzhidi;
  private String gongzuonianxian;
  private String jineng;
  private String min_salary;
  private String job_name;
  private String company_prop;

  public String getXuexiao() {
    return xuexiao;
  }

  public void setXuexiao(String xuexiao) {
    this.xuexiao = xuexiao;
  }

  public String getInfo_major() {
    return info_major;
  }

  public void setInfo_major(String info_major) {
    this.info_major = info_major;
  }

  public String getCompany_prop() {
    return company_prop;
  }

  public void setCompany_prop(String job_prop) {
    this.company_prop = job_prop;
  }

  public String getJob_name() {
    return job_name;
  }

  public void setJob_name(String job_name) {
    this.job_name = job_name;
  }

  public String getMin_salary() {
    return min_salary;
  }

  public void setMin_salary(String min_salary) {
    this.min_salary = min_salary;
  }

  public String getXueli() {
    return xueli;
  }

  public void setXueli(String xueli) {
    this.xueli = xueli;
  }




  public String getQiuzhidi() {
    return qiuzhidi;
  }

  public void setQiuzhidi(String qiuzhidi) {
    this.qiuzhidi = qiuzhidi;
  }

  public String getGongzuonianxian() {
    return gongzuonianxian;
  }

  public void setGongzuonianxian(String gongzuonianxian) {
    this.gongzuonianxian = gongzuonianxian;
  }

  public String getJineng() {
    return jineng;
  }

  public void setJineng(String jineng) {
    this.jineng = jineng;
  }



  @Override
  public String toString() {
    return "JobAnalysis{" +
            "xueli='" + xueli + '\'' +
            ", qiuzhidi='" + qiuzhidi + '\'' +
            ", gongzuonianxian='" + gongzuonianxian + '\'' +
            ", jineng='" + jineng + '\'' +
            '}';
  }
}

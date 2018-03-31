package com.swpu.jobanalysissystem.entity;



import java.io.Serializable;

public class JobInfo implements Serializable  {
    private Integer id;
    private String job_name;
    private String min_salary;
    private String top_salary;
    private String min_xueli;
    private String job_place;
    private String company_name;
    private String job_url;
    private String experience;
    private String job_num;
    private String job_desc;

    private static final long serialVersionUID = 1L;
    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTop_salary() {
        return top_salary;
    }

    public void setTop_salary(String top_salary) {
        this.top_salary = top_salary;
    }

    public String getMin_xueli() {
        return min_xueli;
    }

    public void setMin_xueli(String min_xueli) {
        this.min_xueli = min_xueli;
    }

    public String getJob_place() {
        return job_place;
    }

    public void setJob_place(String job_place) {
        this.job_place = job_place;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJob_url() {
        return job_url;
    }

    public void setJob_url(String job_url) {
        this.job_url = job_url;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJob_num() {
        return job_num;
    }

    public void setJob_num(String job_num) {
        this.job_num = job_num;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
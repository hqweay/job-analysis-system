package com.example.entity;

import java.io.Serializable;

public class ExperienceSalary implements Serializable {
    private String experience;

    private String min_salary;

    private String top_salary;

    private String avg_salary;

    private static final long serialVersionUID = 1L;

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(String min_salary) {
        this.min_salary = min_salary == null ? null : min_salary.trim();
    }

    public String getTop_salary() {
        return top_salary;
    }

    public void setTop_salary(String top_salary) {
        this.top_salary = top_salary == null ? null : top_salary.trim();
    }

    public String getAvg_salary() {
        return avg_salary;
    }

    public void setAvg_salary(String avg_salary) {
        this.avg_salary = avg_salary == null ? null : avg_salary.trim();
    }
}
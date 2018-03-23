package com.example.entity;

import java.io.Serializable;

public class CompanySalary implements Serializable {
    private String company_prop;

    private String min_salary;

    private String top_salary;

    private String avg_salary;

    private static final long serialVersionUID = 1L;

    public String getCompany_prop() {
        return company_prop;
    }

    public void setCompany_prop(String company_prop) {
        this.company_prop = company_prop == null ? null : company_prop.trim();
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
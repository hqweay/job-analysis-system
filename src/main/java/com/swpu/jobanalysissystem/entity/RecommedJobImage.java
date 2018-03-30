package com.swpu.jobanalysissystem.entity;

public class RecommedJobImage {
    private String id;
    private String label;
    private String job_name;
    private String min_salary;
    private String min_xueli;
    private String experience;
    private String job_place;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getMin_xueli() {
        return min_xueli;
    }

    public void setMin_xueli(String min_xueli) {
        this.min_xueli = min_xueli;
    }

    public String getExerpience() {
        return experience;
    }

    public void setExerpience(String experience) {
        this.experience = experience;
    }

    public String getJob_place() {
        return job_place;
    }

    public void setJob_place(String job_place) {
        this.job_place = job_place;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(label).append(" ").append(job_name).append(" ").append(min_salary).append(" ")
                .append(min_xueli).append(" ")
        .append(experience).append(" ")
        .append(job_place);

        return sb.toString();
    }
}

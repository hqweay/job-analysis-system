package com.swpu.jobanalysissystem.entity;

import java.io.Serializable;

public class JobImage implements Serializable {
    private Integer job_id;

    private String label;

    private static final long serialVersionUID = 1L;

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}
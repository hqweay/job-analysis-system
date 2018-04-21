package com.swpu.jobanalysissystem.entity;

import java.io.Serializable;

public class Group implements Serializable {
    private Integer group_id;

    private Integer job_num;

    private String key_word;

    private static final long serialVersionUID = 1L;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getJob_num() {
        return job_num;
    }

    public void setJob_num(Integer job_num) {
        this.job_num = job_num;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word == null ? null : key_word.trim();
    }
}
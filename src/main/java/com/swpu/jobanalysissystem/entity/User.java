package com.swpu.jobanalysissystem.entity;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    @NotNull
    private String name = "";
    @NotNull
    private String email = "";

    private String info_education = "";

    private String info_major = "";

    private String info_place = "";

    private String info_ability = "";

    private String info_experience = "";

    private String likes = "";
    @NotNull
    @Size(min = 6, max = 15)
    private String password = "";

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getInfo_education() {
        return info_education;
    }

    public void setInfo_education(String info_education) {
        this.info_education = info_education == null ? null : info_education.trim();
    }

    public String getInfo_major() {
        return info_major;
    }

    public void setInfo_major(String info_major) {
        this.info_major = info_major == null ? null : info_major.trim();
    }

    public String getInfo_place() {
        return info_place;
    }

    public void setInfo_place(String info_place) {
        this.info_place = info_place == null ? null : info_place.trim();
    }

    public String getInfo_ability() {
        return info_ability;
    }

    public void setInfo_ability(String info_ability) {
        this.info_ability = info_ability == null ? null : info_ability.trim();
    }

    public String getInfo_experience() {
        return info_experience;
    }

    public void setInfo_experience(String info_experience) {
        this.info_experience = info_experience == null ? null : info_experience.trim();
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes == null ? null : likes.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
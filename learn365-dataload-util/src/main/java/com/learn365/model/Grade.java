package com.learn365.model;

import java.util.ArrayList;
import java.util.List;

public class Grade {

    private Integer id;
    private String name;
    private Integer order;
    private boolean active;
    private String picture;
    private String trialVideo;
    private String testId;
    private List<Subject> subject=new ArrayList<>();

    public Grade() {
    }

    public Grade(int id, String name, int order, boolean active, String picture, String trialVideo, String testId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.active = active;
        this.picture = picture;
        this.trialVideo = trialVideo;
        this.testId = testId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTrialVideo() {
        return trialVideo;
    }

    public void setTrialVideo(String trialVideo) {
        this.trialVideo = trialVideo;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }
}

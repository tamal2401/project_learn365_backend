package com.learn365.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private Integer id;
    private String name;
    private Integer order;
    private String subjectpic;
    private String testid;
    private String trialVideo;
    private int gradeId;
    private List<Chapter> chapter=new ArrayList<>();

    public Subject() {
    }

    public Subject(int id, String name, int order, String subjectpic, String testid, String trialVideo, int gradeId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.subjectpic = subjectpic;
        this.testid = testid;
        this.trialVideo = trialVideo;
        this.gradeId = gradeId;
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

    public String getSubjectpic() {
        return subjectpic;
    }

    public void setSubjectpic(String subjectpic) {
        this.subjectpic = subjectpic;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTrialVideo() {
        return trialVideo;
    }

    public void setTrialVideo(String trialVideo) {
        this.trialVideo = trialVideo;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public List<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }
}

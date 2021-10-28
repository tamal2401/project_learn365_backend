package com.learn365.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private Integer id;
    private String chapterName;
    private String chapterPic;
    private Integer order;
    private String testid;
    private String trialvideo;
    private Integer subjectId;
    private List<ChapterVideo> chaptervideo=new ArrayList<>();

    public Chapter() {
    }

    public Chapter(int id, String chapterName, String chapterPic, int order, String testid, String trialvideo, int subjectId) {
        this.id = id;
        this.chapterName = chapterName;
        this.chapterPic = chapterPic;
        this.order = order;
        this.testid = testid;
        this.trialvideo = trialvideo;
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterPic() {
        return chapterPic;
    }

    public void setChapterPic(String chapterPic) {
        this.chapterPic = chapterPic;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTrialvideo() {
        return trialvideo;
    }

    public void setTrialvideo(String trialvideo) {
        this.trialvideo = trialvideo;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public List<ChapterVideo> getChaptervideo() {
        return chaptervideo;
    }

    public void setChaptervideo(List<ChapterVideo> chaptervideo) {
        this.chaptervideo = chaptervideo;
    }
}

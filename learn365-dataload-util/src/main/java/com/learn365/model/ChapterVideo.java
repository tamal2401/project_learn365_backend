package com.learn365.model;

public class ChapterVideo {

    private Integer chapterid;
    private String videoname;
    private String videoUrl;
    private Integer order;
    private String videoDuration;
    private Boolean intialunlock=false;
    private String testid;

    public ChapterVideo() {
    }

    public ChapterVideo(int chapterid, String videoname, String videoUrl, int order, String videoDuration, Boolean intialunlock) {
        this.chapterid = chapterid;
        this.videoname = videoname;
        this.videoUrl = videoUrl;
        this.order = order;
        this.videoDuration = videoDuration;
        this.intialunlock = intialunlock;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Boolean getIntialunlock() {
        return intialunlock;
    }

    public void setIntialunlock(Boolean intialunlock) {
        this.intialunlock = intialunlock;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }
}

package org.learn365.modal.course.response;

public class Recentvideo {

    private String grade;
    private String subjectname;
    private String chaptervideoUrl;
    private String videothumbnail;
    private String chapterurl;
    private String chaptername;

    public Recentvideo(String grade, String subjectname, String chaptervideoUrl, String videothumbnail, String chapterurl, String chaptername) {
        this.grade = grade;
        this.subjectname = subjectname;
        this.chaptervideoUrl = chaptervideoUrl;
        this.videothumbnail = videothumbnail;
        this.chapterurl = chapterurl;
        this.chaptername = chaptername;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getChaptervideoUrl() {
        return chaptervideoUrl;
    }

    public void setChaptervideoUrl(String chaptervideoUrl) {
        this.chaptervideoUrl = chaptervideoUrl;
    }

    public String getVideothumbnail() {
        return videothumbnail;
    }

    public void setVideothumbnail(String videothumbnail) {
        this.videothumbnail = videothumbnail;
    }

    public String getChapterurl() {
        return chapterurl;
    }

    public void setChapterurl(String chapterurl) {
        this.chapterurl = chapterurl;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }
}

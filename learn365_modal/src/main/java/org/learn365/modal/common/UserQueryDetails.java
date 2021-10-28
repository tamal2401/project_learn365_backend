package org.learn365.modal.common;

import javax.validation.constraints.NotBlank;

public class UserQueryDetails {
    @NotBlank(message = "User grade details can not be blank")
    private String userGrade;
    @NotBlank
    private String subjectName;
    @NotBlank
    private String chapterName;
    @NotBlank
    private String topicName;
    private String userQuery;

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }
}

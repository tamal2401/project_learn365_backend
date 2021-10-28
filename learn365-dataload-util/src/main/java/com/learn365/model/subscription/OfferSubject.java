package com.learn365.model.subscription;

public class OfferSubject {

    private Long id;
    private Boolean active;
    private String appName;
    private String offeredSubjectDesccription;
    private Long offeredSubjectid;
    private String offeredSubjectname;
    private int order;
    private String subjectPicture;
    private Integer offered_planId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getOfferedSubjectDesccription() {
        return offeredSubjectDesccription;
    }

    public void setOfferedSubjectDesccription(String offeredSubjectDesccription) {
        this.offeredSubjectDesccription = offeredSubjectDesccription;
    }

    public Long getOfferedSubjectid() {
        return offeredSubjectid;
    }

    public void setOfferedSubjectid(Long offeredSubjectid) {
        this.offeredSubjectid = offeredSubjectid;
    }

    public String getOfferedSubjectname() {
        return offeredSubjectname;
    }

    public void setOfferedSubjectname(String offeredSubjectname) {
        this.offeredSubjectname = offeredSubjectname;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSubjectPicture() {
        return subjectPicture;
    }

    public void setSubjectPicture(String subjectPicture) {
        this.subjectPicture = subjectPicture;
    }

    public Integer getOffered_planId() {
        return offered_planId;
    }

    public void setOffered_planId(Integer offered_planId) {
        this.offered_planId = offered_planId;
    }
}

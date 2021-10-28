package com.learn365.model.subscription;

import java.util.List;

public class OfferGrade {

    private Long id;
    private int order;
    private Boolean active;
    private String appName;
    private Long gradeId;
    private String gradeName;
    private String offeredDescription;
    private String offeredPicture;
    private String offeredVideo;
    private List<OfferPlan> offerplan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getOfferedDescription() {
        return offeredDescription;
    }

    public void setOfferedDescription(String offeredDescription) {
        this.offeredDescription = offeredDescription;
    }

    public String getOfferedPicture() {
        return offeredPicture;
    }

    public void setOfferedPicture(String offeredPicture) {
        this.offeredPicture = offeredPicture;
    }

    public String getOfferedVideo() {
        return offeredVideo;
    }

    public void setOfferedVideo(String offeredVideo) {
        this.offeredVideo = offeredVideo;
    }

    public List<OfferPlan> getOfferplan() {
        return offerplan;
    }

    public void setOfferplan(List<OfferPlan> offerplan) {
        this.offerplan = offerplan;
    }
}

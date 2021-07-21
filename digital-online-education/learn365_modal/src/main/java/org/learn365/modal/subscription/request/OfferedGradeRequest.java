package org.learn365.modal.subscription.request;


import java.util.List;

public class OfferedGradeRequest extends BaseEntity {

    private String gradeId;
    private String gradeName;
    private String offeredDescription;
    private String offeredVideo;
    private List<OfferedPlanRequest> offeredPlan;
    private Boolean active;
    private String offeredPicture;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
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

    public String getOfferedVideo() {
        return offeredVideo;
    }

    public void setOfferedVideo(String offeredVideo) {
        this.offeredVideo = offeredVideo;
    }

    public List<OfferedPlanRequest> getOfferedPlan() {
        return offeredPlan;
    }

    public void setOfferedPlan(List<OfferedPlanRequest> offeredPlan) {
        this.offeredPlan = offeredPlan;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOfferedPicture() {
        return offeredPicture;
    }

    public void setOfferedPicture(String offeredPicture) {
        this.offeredPicture = offeredPicture;
    }
}

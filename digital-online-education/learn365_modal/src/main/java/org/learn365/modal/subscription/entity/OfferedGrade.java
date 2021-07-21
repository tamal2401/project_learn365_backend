package org.learn365.modal.subscription.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offred_grade")
public class OfferedGrade extends BaseEntity {
    @Column(name = "offered_grade", nullable = false, unique = true)
    private String gradeId;
    @Column(name = "offered_gradeName", nullable = false, unique = true)
    private String gradeName;
    @Lob
    @Column(name = "offered_gradedescription", nullable = false)
    private String offeredDescription;
    @Column(name = "offered_gradevideo", nullable = false)
    private String offeredVideo;
    @OneToMany(mappedBy = "offerGrade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubscriptionPlan> offeredPlan;
    @Column(name = "grade_isActive")
    private Boolean active;
    @Lob
    @Column(name = "offered_gradepicture", nullable = false)
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

    public List<SubscriptionPlan> getOfferedPlan() {
        return offeredPlan;
    }

    public void setOfferedPlan(List<SubscriptionPlan> offeredPlan) {
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

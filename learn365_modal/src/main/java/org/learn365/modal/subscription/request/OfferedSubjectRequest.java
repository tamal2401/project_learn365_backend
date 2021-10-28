package org.learn365.modal.subscription.request;





public class OfferedSubjectRequest extends BaseEntity {
    private String offeredSubjectid;
    private String offeredSubjectname;
    private String offeredSubjectDesccription;
    private String SubjectPicture;
    private Boolean active;

    public String getOfferedSubjectid() {
        return offeredSubjectid;
    }

    public void setOfferedSubjectid(String offeredSubjectid) {
        this.offeredSubjectid = offeredSubjectid;
    }

    public String getOfferedSubjectname() {
        return offeredSubjectname;
    }

    public void setOfferedSubjectname(String offeredSubjectname) {
        this.offeredSubjectname = offeredSubjectname;
    }

    public String getOfferedSubjectDesccription() {
        return offeredSubjectDesccription;
    }

    public void setOfferedSubjectDesccription(String offeredSubjectDesccription) {
        this.offeredSubjectDesccription = offeredSubjectDesccription;
    }

    public String getSubjectPicture() {
        return SubjectPicture;
    }

    public void setSubjectPicture(String subjectPicture) {
        SubjectPicture = subjectPicture;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}

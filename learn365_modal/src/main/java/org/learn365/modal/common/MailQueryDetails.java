package org.learn365.modal.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MailQueryDetails {
    @NotNull(message = "Message template type can not be null")
    private MailTemplate templateType;
    @NotBlank(message = "User mail id can not be blank")
    private String userMailId;

    @NotNull
    private UserQueryDetails queryDetails;
    private String bookedClassDate;

    public MailTemplate getTemplateType() {
        return templateType;
    }

    public void setTemplateType(MailTemplate templateType) {
        this.templateType = templateType;
    }

    public String getUserMailId() {
        return userMailId;
    }

    public void setUserMailId(String userMailId) {
        this.userMailId = userMailId;
    }

    public UserQueryDetails getQueryDetails() {
        return queryDetails;
    }

    public void setQueryDetails(UserQueryDetails queryDetails) {
        this.queryDetails = queryDetails;
    }

    public String getBookedClassDate() {
        return bookedClassDate;
    }

    public void setBookedClassDate(String bookedClassDate) {
        this.bookedClassDate = bookedClassDate;
    }
}

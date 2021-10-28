package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewTestDataRequest {

    @NotBlank(message = "User Id for test details request can not be null")
    private String userId;

    @NotBlank(message = "Grade Id for test details request can not be null")
    private String gradeLevel;

    @NotBlank(message = "Subject Id for test details request can not be null")
    private String subjectId;

    private String chapterId;

    private String videoId;
}

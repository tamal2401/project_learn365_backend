package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SubjectStatusRequest {
    @NotEmpty(message = "subject list can not be empty")
    private List<String> subjects;

    @NotBlank(message = "User Id can not be empty")
    private String userId;

    @NotBlank(message = "Grade Id can not be empty")
    private String gradeId;
}

package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChapterTestRequest {
    @NotBlank(message = "User_Id for test details request can not be null")
    private String userId;
    private String testCode;
    private String testId;
}

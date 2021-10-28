package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TestStatRequest {
    @NotBlank(message = "User Id can not be blank")
    private String userId;

    @NotBlank(message = "Grade Id can not be blank")
    private String gradeLevel;

    @NotBlank(message = "Test Type can not be blank")
    private String testType;

    @NotBlank(message = "Date Limit can not be blank")
    private String dateLimit;
}

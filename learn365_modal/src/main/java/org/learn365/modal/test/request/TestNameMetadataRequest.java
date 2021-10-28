package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TestNameMetadataRequest {
    @NotBlank(message = "Grade Name should not be blank")
    public String gradeName;

    @NotBlank(message = "Subject Code should not be blank")
    public String subjectName;

    @NotBlank(message = "Test type should not be blank")
    public String testType;

    @NotBlank(message = "Test Selector Key should not be blank")
    public String testSelectorKey;

}

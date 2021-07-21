package org.learn365.test.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TestDetailsMetadataRequest {
    @NotNull(message = "Test Code should not be null")
    private String testCode;

    @NotNull(message = "isActive flag should be provided for each test")
    private Boolean isActive;

    @NotNull(message = "Question set should not be null for each test")
    private List<QuestionMetadata> testQuestions;
}

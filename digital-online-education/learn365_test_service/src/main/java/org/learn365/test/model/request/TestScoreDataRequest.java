package org.learn365.test.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TestScoreDataRequest {
    @NotNull(message = "User Id should not be null")
    private Long userId;

    @NotNull(message = "Test Id should not be null")
    private Long testId;

    private String testCode;

    @NotNull(message = "Submitted questions list should not be null")
    private List<SubmittedQuestionRequest> submittedQuestions;
}

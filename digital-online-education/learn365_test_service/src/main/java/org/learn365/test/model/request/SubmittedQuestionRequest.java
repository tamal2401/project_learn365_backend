package org.learn365.test.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubmittedQuestionRequest {
    @NotNull(message = "Question Id should not be null")
    private Long questionId;

    @NotNull(message = "Selected option id should not be null")
    private Long selectedOptionId;

    @NotNull(message = "Correct option id should not be null")
    private Long correctOptionId;

    @NotNull(message = "Question Marks should not be null")
    private Integer marks;
}

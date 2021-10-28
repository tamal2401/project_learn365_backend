package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SubmittedQuestionRequest {
    @NotBlank(message = "Question Id should not be null")
    private String questionId;

    @NotBlank(message = "Selected option id should not be null")
    private String selectedOptionId;

    @NotBlank(message = "Correct option id should not be null")
    private String correctOptionId;

    @NotNull(message = "Question Marks should not be null")
    private Integer marks;
}

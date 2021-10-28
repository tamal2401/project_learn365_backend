package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class TestDetailsMetadataRequest {
    @NotNull(message = "Test Code should not be null")
    private String testCode;

    @NotNull(message = "isActive flag should be provided for each test")
    private Boolean isActive;

    @NotNull(message = "Subject Id should be provided for each test")
    private String subjectId;

    @NotNull(message = "Grade Code should be provided for each test")
    private String gradeCode;

    @NotNull(message = "Test type should be provided for each test")
    private String testType;

    @NotNull(message = "Chapter Name should be provided for each test")
    private String chapterName;

    @NotNull(message = "chapter Path should be provided for each test")
    private String chapterPath;

    @NotNull(message = "test Keyfields should be provided for each test")
    private Map<String, String> testKeyFields;

    @NotNull(message = "Question set should not be null for each test")
    private List<@Valid QuestionMetadata> testQuestions;
}

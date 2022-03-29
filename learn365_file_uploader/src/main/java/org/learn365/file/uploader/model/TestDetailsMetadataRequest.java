package org.learn365.file.uploader.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class TestDetailsMetadataRequest {
    private String testCode;
    private Boolean isActive;
    private String subjectId;
    private String gradeCode;
    private String testType;
    private String chapterName = "";
    private String chapterPath = "";
    private Map<String, String> testKeyFields;
    private List<@Valid QuestionMetadata> testQuestions;
}

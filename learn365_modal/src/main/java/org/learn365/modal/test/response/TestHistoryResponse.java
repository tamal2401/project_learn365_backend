package org.learn365.modal.test.response;

import lombok.Data;
import org.learn365.modal.test.TestQuestionData;

import java.util.List;

@Data
public class TestHistoryResponse {
    private String userId;
    private String testId;
    private String testCode;
    private Double marksPercentage;
    private String gradeLevel;
    private String subjectId;
    private String grades;
    private String testType;
    private List<TestQuestionData> questions;
}

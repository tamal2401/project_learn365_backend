package org.learn365.modal.test.response;

import lombok.Data;

@Data
public class TestScoreDataResponse {
    private String userId;
    private String testId;
    private String testCode;
    private String gradeLevel;
    private String grades;
    private String subjectId;
    private double marksInPercentage;
}

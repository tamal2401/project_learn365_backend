package org.learn365.test.model.response;

import lombok.Data;

@Data
public class TestScoreDataResponse {
    private Long userId;
    private Long testId;
    private String testCode;
    private Float marks;
}

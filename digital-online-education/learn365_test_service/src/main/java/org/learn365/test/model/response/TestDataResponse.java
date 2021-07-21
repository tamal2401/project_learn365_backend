package org.learn365.test.model.response;

import lombok.Data;
import org.learn365.test.model.BaseEntity;
import org.learn365.test.model.TestQuestion;

import java.util.List;

@Data
public class TestDataResponse extends BaseEntity {
    private Long UserId;
    private Long testId;
    private String testCode;
    private List<TestQuestion> questions;
}

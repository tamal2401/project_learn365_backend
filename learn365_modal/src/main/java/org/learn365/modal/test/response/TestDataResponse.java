package org.learn365.modal.test.response;

import lombok.Data;
import org.learn365.modal.test.BaseEntity;
import org.learn365.modal.test.TestQuestion;

import java.util.List;

@Data
public class TestDataResponse extends BaseEntity {
    private String testId;
    private String testCode;
    private String gradeId;
    private String subId;
    private String testType;
    private String conceptVideoId;
    private String chapterId;
    private String chapterName;
    private String chapterPath;
    private List<TestQuestion> questions;
}

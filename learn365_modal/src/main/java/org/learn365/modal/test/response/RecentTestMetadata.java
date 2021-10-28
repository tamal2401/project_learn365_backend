package org.learn365.modal.test.response;

import lombok.Data;

@Data
public class RecentTestMetadata {
    private String testId;
    private String subjectName;
    private String chapterName;
    private Integer totalMarks;
    private Integer totalNumOfQuestions;
}

package org.learn365.modal.test.response;

import lombok.Data;
import org.learn365.modal.test.BaseEntity;
import org.learn365.modal.test.CompletedChapterTestStatus;
import org.learn365.modal.test.PendingChapterTestData;

import java.util.List;

@Data
public class SubjectStatusResponse extends BaseEntity {
    private String subject;
    private Integer totalTests;
    private Integer completedTests;
    private List<CompletedChapterTestStatus> allChapterTestStatus;
    private List<PendingChapterTestData> pendingChapterNames;
}

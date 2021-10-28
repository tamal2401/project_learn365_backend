package org.learn365.modal.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "UserTestReport")
@Data
@EqualsAndHashCode(callSuper=false)
public class UserTestReportEntity extends BaseEntity {
    @Indexed(unique = true)
    private String userId;
    @Indexed(unique = true)
    private String testId;
    private String testCode;
    private SubjectDetailsEntity subDetails;
    private Double totalMarks;
    private Double obtainedMarks;
    private Double marksInPercentage;
    private String grades;
}

package org.learn365.modal.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "UserTestOptionsRecord")
@Data
@EqualsAndHashCode(callSuper=false)
public class UserTestOptsRecordEntity extends BaseEntity {
    @Indexed(unique = true)
    private String userId;
    @Indexed(unique = true)
    private String testId;
    private String testCode;
    private SubjectDetailsEntity subDetails;
    private List<TestQuestionsOptionsEntity> questionDetails;
}

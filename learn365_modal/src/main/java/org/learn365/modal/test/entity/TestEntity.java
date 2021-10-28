package org.learn365.modal.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tests")
@Data
@EqualsAndHashCode(callSuper=false)
public class TestEntity extends BaseEntity {
    @Indexed(name = "testCode", unique = true)
    private String testCode;
    private Boolean isActive;
    private Boolean isDeleted;
    private SubjectDetailsEntity subDetails;
    private List<QuestionEntity> questions;
}

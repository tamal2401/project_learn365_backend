package org.learn365.modal.test.entity;

import lombok.Data;

@Data
public class TestQuestionsOptionsEntity {
    private String questionId;
    private String selectedOptionId;
    private String correctOptionId;
    private Integer marks;
}

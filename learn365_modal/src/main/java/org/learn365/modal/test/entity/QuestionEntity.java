package org.learn365.modal.test.entity;

import lombok.Data;

import java.util.List;

@Data
public class QuestionEntity {
    private String questionId;
    private String question;
    private Integer questionOrder;
    private Integer marks;
    private String explanationText;
    private String explanationVideoId;
    private String explanationVideoPath;
    private List<OptionsEntity> options;
}

package org.learn365.modal.test;

import lombok.Data;

import java.util.List;

@Data
public class TestQuestionData {
    private String questionId;
    private String question;
    private Integer questionOrder;
    private Integer marks;
    private String explanationText;
    private String explanationVideoId;
    private String explanationVideoPath;
    private String selectedOptionId;
    private List<Option> options;
}

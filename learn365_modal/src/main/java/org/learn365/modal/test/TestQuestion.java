package org.learn365.modal.test;

import lombok.Data;

import java.util.Set;

@Data
public class TestQuestion {
    private String questionId;
    private String question;
    private Integer questionOrder;
    private Integer marks;
    private String explanationText;
    private String explanationVideoId;
    private String explanationVideoPath;
    private Set<Option> options;
}

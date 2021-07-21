package org.learn365.test.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TestQuestion {
    private Long questionId;
    private String question;
    private String questionOrder;
    private Integer marks;
    private String explanationText;
    private Long explanationVideoId;
    private String explanationVideoPath;
    private Set<Option> options;
}

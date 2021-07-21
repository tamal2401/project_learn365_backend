package org.learn365.test.model;

import lombok.Data;

@Data
public class Option {
    private Long optionId;
    private Integer optionSeq;
    private String optionDesc;
    private Boolean isCorrect;
}

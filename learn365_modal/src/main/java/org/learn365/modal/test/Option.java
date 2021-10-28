package org.learn365.modal.test;

import lombok.Data;

@Data
public class Option {
    private String optionId;
    private Integer optionSeq;
    private String optionDesc;
    private Boolean isCorrect;
}

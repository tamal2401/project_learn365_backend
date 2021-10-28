package org.learn365.modal.test.entity;

import lombok.Data;

@Data
public class OptionsEntity {
    private String optionId;
    private String optionDescription;
    private Integer optionSequence;
    private Boolean isCorrect;

}

package org.learn365.modal.test.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OptionMetadata {
    @NotNull(message = "optionSeq field is null")
    private Integer optionSeq;

    @NotNull(message = "optionDesc field is null")
    private String optionDesc;

    @NotNull(message = "isCorrect flag is null")
    private Boolean isCorrect;
}

package org.learn365.file.uploader.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionMetadata {
    @NotNull(message = "question field is null")
    private String question;

    @NotNull(message = "questionOrder field is null")
    private Integer questionOrder;

    @NotNull(message = "marks field is null")
    private Integer marks;

    @NotNull(message = "explanationText field is null")
    private String explanationText;

    @NotNull(message = "explanationVideoId field is null")
    private String explanationVideoId;

    @NotNull(message = "explanationVideoPath field is null")
    private String explanationVideoPath;

    @NotNull(message = "options field is null")
    private List<@Valid OptionMetadata> options;
}

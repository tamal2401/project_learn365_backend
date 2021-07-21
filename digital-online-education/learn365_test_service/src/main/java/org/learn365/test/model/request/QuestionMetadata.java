package org.learn365.test.model.request;

import lombok.Data;
import org.learn365.test.model.Option;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class QuestionMetadata {
    @NotNull(message = "question field is null")
    private String question;

    @NotNull(message = "questionOrder field is null")
    private String questionOrder;

    @NotNull(message = "marks field is null")
    private Integer marks;

    @NotNull(message = "explanationText field is null")
    private String explanationText;

    @NotNull(message = "explanationVideoId field is null")
    private Long explanationVideoId;

    @NotNull(message = "explanationVideoPath field is null")
    private String explanationVideoPath;

    @NotNull(message = "options field is null")
    private Set<OptionMetadata> options;
}

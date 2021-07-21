package org.learn365.test.model.request;

import lombok.Data;
import org.learn365.test.model.BaseEntity;

import javax.validation.constraints.NotNull;

@Data
public class ChapterTestRequest extends BaseEntity {
    @NotNull(message = "User_Id for test details request can not be null")
    private Long userId;

    @NotNull(message = "Test Code for get test details request can not be null")
    private String testCode;
}

package org.learn365.modal.course;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CourseVideoTestStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long videoId;
    private TestResult testResult;
    private String testId;
}

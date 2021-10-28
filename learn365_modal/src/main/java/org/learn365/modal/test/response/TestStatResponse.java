package org.learn365.modal.test.response;

import lombok.Data;
import org.learn365.modal.test.Identifier;

import java.util.*;

@Data
public class TestStatResponse {
    private String userId;
    private Integer totalTestAttempted;
    private Integer totalTestAvailable;
    private Integer totalMarks;
    private Integer obtainedMarks;
    private Set<String> favouriteSub;
    private Set<String> weakSub;
    private List<Identifier> subGradeMap;
}



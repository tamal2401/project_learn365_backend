package org.learn365.modal.test.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class TestUsageResponse {
    private String userId;
    private Long completedTestCount;
    private Long pendingTestCount;
    private Set<String> favouriteSub;
    private Set<String> weakSub;
}

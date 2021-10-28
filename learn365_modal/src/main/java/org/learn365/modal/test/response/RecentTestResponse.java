package org.learn365.modal.test.response;

import lombok.Data;

import java.util.List;

@Data
public class RecentTestResponse {
    private Long totalTestCount;
    private Long completedTestCount;
    private List<RecentTestMetadata> recentTestsData;
    private List<VideoMetadata> recentVideoDetails;
}

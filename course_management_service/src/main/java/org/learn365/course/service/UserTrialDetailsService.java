package org.learn365.course.service;

import org.learn365.modal.course.request.TrialPeriodRequest;
import org.learn365.modal.course.response.Recentvideo;
import org.learn365.modal.course.response.TrialPeriodResponse;

import java.util.List;

public interface UserTrialDetailsService {

    TrialPeriodResponse createTrialData(TrialPeriodRequest trialPeriodRequest);

    TrialPeriodResponse getTrailStatus(Long userid);

    List<Recentvideo> getTrialVideos(String grade);
}

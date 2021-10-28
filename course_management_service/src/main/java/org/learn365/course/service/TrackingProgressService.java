package org.learn365.course.service;

import org.learn365.modal.course.request.TrackStudentProgressRequest;
import org.learn365.modal.course.response.Recentvideo;
import org.learn365.modal.course.response.SubscribedSubjectReport;
import org.learn365.modal.course.response.UserVideos;

import javax.persistence.Tuple;
import java.util.List;

public interface TrackingProgressService {

    public Boolean updateProgress(TrackStudentProgressRequest trackservice);
    List<SubscribedSubjectReport> getSubscribedSubject(Long userId, String gradeName);
    UserVideos getRecentVideos(Long userid);
}

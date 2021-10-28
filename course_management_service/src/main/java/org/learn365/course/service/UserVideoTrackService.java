package org.learn365.course.service;

import org.learn365.modal.course.request.UserVideoTrackerRequest;
import org.learn365.modal.course.response.SubscribedSubjectReport;
import org.learn365.modal.course.response.UserVideoTrackerResponse;

import java.util.List;

public interface UserVideoTrackService {

	public Boolean addUserVideoTrack(UserVideoTrackerRequest userVideoTrackerRequest);

	public UserVideoTrackerResponse getUserVideoTrack(Long userId);

}

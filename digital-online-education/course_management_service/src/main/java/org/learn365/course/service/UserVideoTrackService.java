package org.learn365.course.service;

import org.learn365.modal.course.request.UserVideoTrackerRequest;
import org.learn365.modal.course.response.UserVideoTrackerResponse;

public interface UserVideoTrackService {

	public Boolean addUserVideoTrack(UserVideoTrackerRequest userVideoTrackerRequest);

	public UserVideoTrackerResponse getUserVideoTrack(Long userId);

}

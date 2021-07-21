package org.learn365.course.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.course.repository.UserVideoTrackerRepository;
import org.learn365.course.service.UserVideoTrackService;
import org.learn365.exception.UserVideoTrackException;
import org.learn365.modal.course.entity.UserVideoTracker;
import org.learn365.modal.course.request.UserVideoTrackerRequest;
import org.learn365.modal.course.response.UserVideoTrackerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserVideoTrackServiceImpl implements UserVideoTrackService {

	private UserVideoTrackerRepository userVideoTrackerRepository;

	public UserVideoTrackServiceImpl(UserVideoTrackerRepository userVideoTrackerRepository) {
		this.userVideoTrackerRepository = userVideoTrackerRepository;
	}

	@Override
	public Boolean addUserVideoTrack(UserVideoTrackerRequest userVideoTrackerRequest) {
		Boolean isIncompleteVideoTracked = true;
		if (ObjectUtils.isEmpty(userVideoTrackerRequest)) {
			throw new IllegalArgumentException("video tracker request should not be null");
		}
		UserVideoTracker uservideotrack = new UserVideoTracker();
		Optional<UserVideoTracker> savedvideoTrack;
		try {
			Optional<UserVideoTracker> userVideoTrack = userVideoTrackerRepository
					.findByUserId(userVideoTrackerRequest.getUserId());
			BeanUtils.copyProperties(userVideoTrackerRequest, uservideotrack);
			if (userVideoTrack.isPresent()) {
				uservideotrack.setId(userVideoTrack.get().getId());
			}
			savedvideoTrack = Optional.of(userVideoTrackerRepository.save(uservideotrack));
		} catch (Exception exception) {
			isIncompleteVideoTracked = false;
			throw new UserVideoTrackException(exception.getMessage());
		}

		savedvideoTrack.orElseThrow(() -> new UserVideoTrackException("Unable to save video track for user"));

		return isIncompleteVideoTracked;
	}

	@Override
	public UserVideoTrackerResponse getUserVideoTrack(Long userId) {

		UserVideoTrackerResponse userVideoTrackerResponse = new UserVideoTrackerResponse();
		Optional<UserVideoTracker> userVideoTrack;
		try {
			userVideoTrack = userVideoTrackerRepository.findByUserId(userId);
		} catch (Exception exception) {
			throw new UserVideoTrackException(exception.getMessage());
		}
		userVideoTrack.orElseThrow(() -> new UserVideoTrackException("No User video track record found"));
		BeanUtils.copyProperties(userVideoTrack.get(), userVideoTrackerResponse);

		return userVideoTrackerResponse;
	}

}

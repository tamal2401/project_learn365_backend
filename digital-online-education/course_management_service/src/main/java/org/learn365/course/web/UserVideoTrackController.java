package org.learn365.course.web;

import javax.validation.Valid;

import org.learn365.course.service.UserVideoTrackService;
import org.learn365.modal.course.request.UserVideoTrackerRequest;
import org.learn365.modal.course.response.UserVideoTrackerResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/uservideoTracker/")
public class UserVideoTrackController {

	private UserVideoTrackService userVideoTrackService;

	public UserVideoTrackController(UserVideoTrackService userVideoTrackService) {

		this.userVideoTrackService = userVideoTrackService;
	}

	@PostMapping(value = "uservideotrack", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean addUserVideoTrack(@Valid @RequestBody UserVideoTrackerRequest userVideoTrackerRequest) {
		return userVideoTrackService.addUserVideoTrack(userVideoTrackerRequest);
	}

	@GetMapping(value = "uservideotrack/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserVideoTrackerResponse getUserVideoTrack(@PathVariable(name = "userId") Long userId) {
		return userVideoTrackService.getUserVideoTrack(userId);
	}

}

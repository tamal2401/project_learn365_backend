package org.learn365.course.web;

import javax.validation.Valid;

import org.learn365.course.service.UserCourseService;
import org.learn365.modal.course.request.UserInprogressCourseRequest;
import org.learn365.modal.course.response.UserInprogressCourseResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user/")
public class UserCourseController {

	private UserCourseService userCourseService;

	public UserCourseController(UserCourseService userCourseService) {
		this.userCourseService = userCourseService;
	}

	@PostMapping(value = "userinprogresscourse", produces = MediaType.APPLICATION_JSON_VALUE)
	public void addUserInprogressCourse(@Valid @RequestBody UserInprogressCourseRequest userInprogressCourseRequest) {
		userCourseService.addUserInprogressCourse(userInprogressCourseRequest);
	}

	@PutMapping(value = "userinprogresscourse/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInprogressCourseResponse getUserInprogressCourseByUser(@PathVariable(name = "userid") String userid) {
		return userCourseService.getUserInprogressCourseByUser(userid);
	}

}

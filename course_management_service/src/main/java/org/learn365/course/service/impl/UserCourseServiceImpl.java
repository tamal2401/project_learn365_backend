package org.learn365.course.service.impl;

import java.util.Optional;

import org.learn365.course.repository.UserInprogressCourseRepository;
import org.learn365.course.service.UserCourseService;
import org.learn365.exception.UserCourseInprogressException;
import org.learn365.modal.course.entity.UserInprogressCourse;
import org.learn365.modal.course.request.UserInprogressCourseRequest;
import org.learn365.modal.course.response.UserInprogressCourseResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserCourseServiceImpl implements UserCourseService {

	private UserInprogressCourseRepository userInprogressCourseRepository;

	UserCourseServiceImpl(UserInprogressCourseRepository userInprogressCourseRepository) {
		this.userInprogressCourseRepository = userInprogressCourseRepository;
	}

	@Override
	public void addUserInprogressCourse(UserInprogressCourseRequest userInprogressCourseRequest) {
		Optional<UserInprogressCourse> userInprogress = userInprogressCourseRepository
				.findByUserid(userInprogressCourseRequest.getUserid());

		UserInprogressCourse userRecord = new UserInprogressCourse();
		BeanUtils.copyProperties(userInprogressCourseRequest, userRecord);
		if (userInprogress.get() != null) {
			userRecord.setId(userInprogress.get().getId());
		}

		Optional<UserInprogressCourse> saveduserInprogressCourse = Optional
				.of(userInprogressCourseRepository.save(userRecord));

		saveduserInprogressCourse.orElseThrow(
				() -> new UserCourseInprogressException("Failed to save or update the user Inprogress course"));

	}

	@Override
	public UserInprogressCourseResponse getUserInprogressCourseByUser(String userid) {
		Optional<UserInprogressCourse> optionaluserInprogress = userInprogressCourseRepository.findByUserid(userid);
		UserInprogressCourse userInprogress = optionaluserInprogress
				.orElseThrow(() -> new UserCourseInprogressException("User doesNot have any course in progress"));
		UserInprogressCourseResponse userInprogressCourseResponse = new UserInprogressCourseResponse();
		BeanUtils.copyProperties(userInprogress, userInprogressCourseResponse);
		return userInprogressCourseResponse;
	}

}

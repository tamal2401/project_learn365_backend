package org.learn365.course.service;

import org.learn365.modal.course.request.UserInprogressCourseRequest;
import org.learn365.modal.course.response.UserInprogressCourseResponse;

public interface UserCourseService {

	public void addUserInprogressCourse(UserInprogressCourseRequest userInprogressCourseRequest);

	public UserInprogressCourseResponse getUserInprogressCourseByUser(String userid);

}

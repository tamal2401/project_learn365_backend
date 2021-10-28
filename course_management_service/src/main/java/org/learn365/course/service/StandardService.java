package org.learn365.course.service;

import java.util.List;

import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.response.StandardResponse;

public interface StandardService {

	 List<StandardResponse> getAllAvailableCourses();

	 StandardResponse getCourseByGrade(String grade);

	 StandardResponse addCourse(StandardRequest standardrequest);

	 List<String> getAllGrade();

}

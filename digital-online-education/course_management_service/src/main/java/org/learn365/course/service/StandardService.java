package org.learn365.course.service;

import java.util.List;

import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.response.StandardResponse;

public interface StandardService {

	public List<StandardResponse> getAllAvailableCourses();

	public StandardResponse getCourseByGrade(String grade);

	public StandardResponse addCourse(StandardRequest standardrequest);

}

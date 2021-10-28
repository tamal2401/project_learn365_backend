package org.learn365.course.service;

import java.util.List;

import org.learn365.modal.course.request.SubjectRequest;
import org.learn365.modal.course.response.SubjectResponse;

public interface SubjectService {

	public List<SubjectResponse> getAllAvailableCourses();

	public List<SubjectResponse> getCourseByGrade(String grade);

	public SubjectResponse getCourseById(Long id);

	public SubjectResponse addCourse(SubjectRequest subjectrequest);

}

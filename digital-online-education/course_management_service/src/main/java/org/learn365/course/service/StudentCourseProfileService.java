package org.learn365.course.service;

import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.subscription.request.StudentProfileCreateRequest;

import java.util.List;

public interface StudentCourseProfileService {

    void createStudentProfile(StudentProfileCreateRequest studentProfileCreateRequest);
    List<StandardResponse> getUserCourseDetails(Long userId);

}

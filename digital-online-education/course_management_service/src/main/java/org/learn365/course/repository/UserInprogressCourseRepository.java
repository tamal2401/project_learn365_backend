package org.learn365.course.repository;

import java.util.Optional;

import org.learn365.modal.course.entity.UserInprogressCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInprogressCourseRepository extends JpaRepository<UserInprogressCourse, Long> {

	public Optional<UserInprogressCourse> findByUserid(String userId);

}

package org.learn365.course.repository;

import org.learn365.modal.course.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long> {

    Optional<StudentProfile> findByUserId(Long userId);
}

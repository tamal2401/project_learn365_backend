package org.learn365.course.repository;

import java.util.Optional;

import org.learn365.modal.course.entity.UserVideoTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVideoTrackerRepository extends JpaRepository<UserVideoTracker, Long> {

	public Optional<UserVideoTracker> findByUserId(Long userId);

}

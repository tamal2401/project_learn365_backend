package org.learn365.course.repository;

import org.learn365.modal.course.entity.UserTrialDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTrialDetailsRepository extends JpaRepository<UserTrialDetails, Long> {

    Optional<UserTrialDetails> findUserTrialDetailsByUserid(Long userId);
}

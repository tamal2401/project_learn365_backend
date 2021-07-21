package org.learn365.test.repository;

import org.learn365.test.entity.UserTestReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestReportRepository extends JpaRepository<UserTestReportEntity, Long> {
}

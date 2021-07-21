package org.learn365.subscription.repository;

import org.learn365.modal.subscription.entity.OfferedSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferedSubjectRepository extends JpaRepository<OfferedSubject,Long> {
}

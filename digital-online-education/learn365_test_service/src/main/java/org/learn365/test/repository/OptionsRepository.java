package org.learn365.test.repository;

import org.learn365.test.entity.OptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<OptionsEntity, Long> {
}

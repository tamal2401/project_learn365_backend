package org.learn365.course.repository;

import java.util.Optional;

import org.learn365.modal.course.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRepository extends JpaRepository<Standard, Long> {

	public Optional<Standard> findByName(String name);

}

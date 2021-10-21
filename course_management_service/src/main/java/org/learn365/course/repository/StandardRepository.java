package org.learn365.course.repository;

import java.util.List;
import java.util.Optional;

import org.learn365.modal.course.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StandardRepository extends JpaRepository<Standard, Long> {

	 Optional<Standard> findByName(String name);

	 @Query(value = "select name from Standard")
	 List<String> findAllGrade();

}

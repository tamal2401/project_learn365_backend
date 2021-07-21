package org.learn365.security.repository;

import java.util.Optional;

import org.learn365.modal.user.entity.Learn365User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Learn365UserRepository extends JpaRepository<Learn365User, Long> {

	Optional<Learn365User> findByEmailAndDeleted(String email, boolean isdeleted);
}

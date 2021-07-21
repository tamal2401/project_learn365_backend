package org.learn365.user.repository;

import org.learn365.modal.user.entity.Learn365User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Learn365UserRepository extends JpaRepository<Learn365User, Long> {

    public Optional<Learn365User> findByDeviceId(String deviceId);

    public Optional<Learn365User> findByEmail(String email);

}

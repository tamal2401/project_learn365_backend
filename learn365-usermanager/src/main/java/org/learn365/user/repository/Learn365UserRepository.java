package org.learn365.user.repository;

import org.learn365.modal.user.entity.Learn365User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface Learn365UserRepository
        extends JpaRepository<Learn365User, Long>
{

    Optional<Learn365User> findByDeviceId(String deviceId);

    Optional<Learn365User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "update Learn365User user set user.active=true,user.otpverified=true where user.email=:email")
    void activateUser(@Param("email") String email);

}

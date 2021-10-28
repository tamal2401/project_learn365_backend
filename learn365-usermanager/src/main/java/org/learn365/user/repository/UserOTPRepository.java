package org.learn365.user.repository;

import org.learn365.modal.user.entity.UserOtp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserOTPRepository extends JpaRepository<UserOtp, Long>
{
    @Query(value = "select uo from UserOtp uo where uo.emailid=:email order by uo.receiveddate DESC ")
    List<UserOtp> findEmailid(@Param("email") String emailid,Pageable page);
}

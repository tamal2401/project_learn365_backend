package org.learn365.subscription.repository;

import org.learn365.modal.subscription.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

    public Optional<UserSubscription> findByUserId(Long userId);

    @Query(value = "select us from UserSubscription us where us.userId=:userId and us.subscriptionEnddate >= :currentDate")
    public List<UserSubscription> findByUserIdandValidEndDate(@Param("userId") Long userId, @Param("currentDate") LocalDate currentDate);

}

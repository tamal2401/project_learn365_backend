package org.learn365.subscription.repository;

import org.learn365.modal.subscription.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan,Long> {


}

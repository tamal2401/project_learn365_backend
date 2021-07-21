package org.learn365.subscription.repository;

import org.learn365.modal.subscription.entity.SpecialOfferings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialOfferingsRepository extends JpaRepository<SpecialOfferings,Long> {

    List<SpecialOfferings> findBySpecialOfferingType(String type);
}

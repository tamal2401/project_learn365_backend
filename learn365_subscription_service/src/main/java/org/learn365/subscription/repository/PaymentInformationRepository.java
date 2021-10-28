package org.learn365.subscription.repository;

import org.learn365.modal.subscription.entity.PaymentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInformationRepository extends JpaRepository<PaymentInformation, Long> {

}

package org.learn365.subscription.service;

import org.learn365.modal.subscription.request.SpecialOfferingsRequest;
import org.learn365.modal.subscription.response.SpecialOfferingsResponse;

import java.util.List;

public interface SpecialOfferingService {

    List<SpecialOfferingsResponse> getAllAvailableSpecialOffering();
    List<SpecialOfferingsResponse> getAvailableSpecialOfferingByType(String type);
    SpecialOfferingsResponse createSpecialOffering(SpecialOfferingsRequest specialOfferingsRequest);

}

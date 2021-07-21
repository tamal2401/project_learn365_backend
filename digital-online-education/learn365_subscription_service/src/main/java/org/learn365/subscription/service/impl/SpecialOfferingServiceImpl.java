package org.learn365.subscription.service.impl;

import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.modal.subscription.entity.SpecialOfferings;
import org.learn365.modal.subscription.request.SpecialOfferingsRequest;
import org.learn365.modal.subscription.response.SpecialOfferingsResponse;
import org.learn365.subscription.repository.SpecialOfferingsRepository;
import org.learn365.subscription.service.SpecialOfferingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialOfferingServiceImpl implements SpecialOfferingService {

    private SpecialOfferingsRepository specialOfferingsRepository;
    public SpecialOfferingServiceImpl(SpecialOfferingsRepository specialOfferingsRepository){
      this.specialOfferingsRepository=specialOfferingsRepository;
    }
    @Override
    public List<SpecialOfferingsResponse> getAllAvailableSpecialOffering() {
        List<SpecialOfferings> specialoffering=specialOfferingsRepository.findAll();
        if(CollectionUtils.isEmpty(specialoffering)){
            throw new CoursePortFolioNotFoundException("No special offering available now");
        }
        return specialoffering.stream().map(spoffering-> {
            SpecialOfferingsResponse spOfferingResponse=new SpecialOfferingsResponse();
            BeanUtils.copyProperties(spoffering,spOfferingResponse);
            return spOfferingResponse;
        }).collect(Collectors.toList());

    }

    @Override
    public List<SpecialOfferingsResponse> getAvailableSpecialOfferingByType(String type) {
        List<SpecialOfferings> specialoffering=specialOfferingsRepository.findBySpecialOfferingType(type);
        if(CollectionUtils.isEmpty(specialoffering)){
            throw new CoursePortFolioNotFoundException("No special offering available now");
        }
        return specialoffering.stream().map(spoffering-> {
            SpecialOfferingsResponse spOfferingResponse=new SpecialOfferingsResponse();
            BeanUtils.copyProperties(spoffering,spOfferingResponse);
            return spOfferingResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public SpecialOfferingsResponse createSpecialOffering(SpecialOfferingsRequest specialOfferingsRequest) {
        SpecialOfferings specialOfferings=new SpecialOfferings();
        BeanUtils.copyProperties(specialOfferingsRequest,specialOfferings);
        SpecialOfferings savedSpecialOffering=specialOfferingsRepository.save(specialOfferings);
            SpecialOfferingsResponse spOfferingResponse=new SpecialOfferingsResponse();
            BeanUtils.copyProperties(savedSpecialOffering,spOfferingResponse);
            return spOfferingResponse;
          }
}

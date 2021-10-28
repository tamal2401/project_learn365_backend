package org.learn365.subscription.web;

import org.learn365.modal.subscription.request.SpecialOfferingsRequest;
import org.learn365.modal.subscription.request.StudentProfileCreateRequest;
import org.learn365.modal.subscription.response.SpecialOfferingsResponse;
import org.learn365.subscription.config.CreateUserSubscription;
import org.learn365.subscription.service.SpecialOfferingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class SpecialOfferingController {

    private static Logger log = LoggerFactory.getLogger(
            SpecialOfferingController.class);
    @Autowired
    CreateUserSubscription createUserSubscription;

    private SpecialOfferingService specialOfferingService;
    public SpecialOfferingController(SpecialOfferingService specialOfferingService){
        this.specialOfferingService=specialOfferingService;
    }

    @GetMapping(value = {"/getAllSpecialOfferings","/service/getAllSpecialOfferings"})
    public List<SpecialOfferingsResponse> getSpecialOffering(){
        log.info("Request to get all special offering by learn365");
        return specialOfferingService.getAllAvailableSpecialOffering();
    }
    @GetMapping(value = {"/getSpecialOfferingsBytype","/service/getSpecialOfferingsBytype"})
    public List<SpecialOfferingsResponse> getSpecialOfferingBytype(@RequestParam(name = "offeringtype",required = true) String offeringtype){
        log.info("Request to get all special offering by type");
        return specialOfferingService.getAvailableSpecialOfferingByType(offeringtype);

    }

    @PostMapping(value = {"/createSpecialOfferings","/service/createSpecialOfferings"})
    public SpecialOfferingsResponse createSpecialOffering(@RequestBody SpecialOfferingsRequest specialOfferingsRequest){
        return specialOfferingService.createSpecialOffering(specialOfferingsRequest);
    }

    @PostMapping(value = {"/message","/service/message"})
    public void message(){
        StudentProfileCreateRequest studentProfileCreateRequest=new StudentProfileCreateRequest();
        studentProfileCreateRequest.setUserId(1L);
        studentProfileCreateRequest.setGradeId(2L);
        studentProfileCreateRequest.setStartDate(LocalDate.now());
        createUserSubscription.applySubscriptionToUser().send(MessageBuilder.withPayload(studentProfileCreateRequest).build());
    }

}

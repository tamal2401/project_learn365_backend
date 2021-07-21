package org.learn365.subscription.web;

import org.learn365.modal.subscription.request.SpecialOfferingsRequest;
import org.learn365.modal.subscription.response.SpecialOfferingsResponse;
import org.learn365.subscription.service.SpecialOfferingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class SpecialOfferingController {

    private SpecialOfferingService specialOfferingService;
    public SpecialOfferingController(SpecialOfferingService specialOfferingService){
        this.specialOfferingService=specialOfferingService;
    }

    @GetMapping(value = {"/getAllSpecialOfferings","/service/getAllSpecialOfferings"})
    public List<SpecialOfferingsResponse> getSpecialOffering(){
        return specialOfferingService.getAllAvailableSpecialOffering();
    }
    @GetMapping(value = {"/getSpecialOfferingsBytype","/service/getSpecialOfferingsBytype"})
    public List<SpecialOfferingsResponse> getSpecialOfferingBytype(@RequestParam(name = "offeringtype",required = true) String offeringtype){
        return specialOfferingService.getAvailableSpecialOfferingByType(offeringtype);

    }

    @PostMapping(value = {"/createSpecialOfferings","/service/createSpecialOfferings"})
    public SpecialOfferingsResponse createSpecialOffering(@RequestBody SpecialOfferingsRequest specialOfferingsRequest){
        return specialOfferingService.createSpecialOffering(specialOfferingsRequest);
    }
}

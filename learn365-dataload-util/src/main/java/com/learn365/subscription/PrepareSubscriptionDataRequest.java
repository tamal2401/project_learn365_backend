package com.learn365.subscription;

import com.learn365.model.subscription.OfferGrade;
import com.learn365.model.subscription.OfferPlan;
import com.learn365.model.subscription.OfferSubject;
import org.learn365.modal.subscription.request.OfferedGradeRequest;
import org.learn365.modal.subscription.request.OfferedPlanRequest;
import org.learn365.modal.subscription.request.OfferedSubjectRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PrepareSubscriptionDataRequest {


    public List<OfferedGradeRequest> createCoursePortfolio(Map<Long, OfferGrade> offerGradeMap) {
        List<OfferedGradeRequest> standard = new ArrayList<>();
        offerGradeMap.forEach((k, v) -> {
            standard.add(mapOfferGradeRequest(v));
        });
        return standard;
    }

    private OfferedGradeRequest mapOfferGradeRequest(OfferGrade offergrade) {
        OfferedGradeRequest offeredGradeRequest = new OfferedGradeRequest();
        offeredGradeRequest.setGradeId(offergrade.getGradeId().toString());
        offeredGradeRequest.setGradeName(offergrade.getGradeName());
        offeredGradeRequest.setActive(offergrade.getActive());
        offeredGradeRequest.setOrder(offergrade.getOrder());
        offeredGradeRequest.setOfferedDescription(offergrade.getOfferedDescription());
        offeredGradeRequest.setOfferedPicture(offergrade.getOfferedPicture());
        offeredGradeRequest.setOfferedVideo(offergrade.getOfferedVideo());
        offeredGradeRequest.setAppName(offergrade.getAppName());
        offeredGradeRequest.setOfferedPlan(mapOfferPlan(offergrade.getOfferplan()));
        return offeredGradeRequest;
    }

    private List<OfferedPlanRequest> mapOfferPlan(List<OfferPlan> offerPlan) {

        List<OfferedPlanRequest> offerPlanRe = null;
        if (null != offerPlan) {
            offerPlanRe = offerPlan.stream().filter(op -> null != op).map(ofpl -> {
                OfferedPlanRequest offeredPlanRequest = new OfferedPlanRequest();
                offeredPlanRequest.setActive(ofpl.getActive());
                offeredPlanRequest.setCost(ofpl.getCost());
                offeredPlanRequest.setOrder(ofpl.getOrder());
                offeredPlanRequest.setSubscriptionPlanName(ofpl.getSubscriptionPlanName());
                offeredPlanRequest.setCurrency(ofpl.getCurrency());
                offeredPlanRequest.setDiscountPrice(ofpl.getDiscountPrice());
                offeredPlanRequest.setAppName(ofpl.getAppName());
                offeredPlanRequest.setDiscounted(false);
                offeredPlanRequest.setValidity(ofpl.getValidity().toString());
                offeredPlanRequest.setCreatedBy("ADMIN");
                offeredPlanRequest.setCourseSubscriptionDetail(mapOfferSubjectRequest(ofpl.getOfferSubjects()));
                return offeredPlanRequest;
            }).collect(Collectors.toList());
        }
        return offerPlanRe;
    }

    public List<OfferedSubjectRequest> mapOfferSubjectRequest(List<OfferSubject> offerSubject) {

        List<OfferedSubjectRequest> offersubList = null;
        if (null != offerSubject) {
            offersubList = offerSubject.stream().filter(os -> null != os).map(ofs -> {
                OfferedSubjectRequest offersub = new OfferedSubjectRequest();
                offersub.setActive(ofs.getActive());
                offersub.setOrder(ofs.getOrder());
                offersub.setOfferedSubjectid(ofs.getOfferedSubjectid().toString());
                offersub.setOfferedSubjectname(ofs.getOfferedSubjectname());
                offersub.setAppName(ofs.getAppName());
                offersub.setOfferedSubjectDesccription(ofs.getOfferedSubjectDesccription());
                offersub.setSubjectPicture(ofs.getSubjectPicture());
                return offersub;
            }).collect(Collectors.toList());
        }
        return offersubList;
    }

}

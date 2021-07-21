package org.learn365.subscription.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.exception.SubscriptionCoursePortFolioException;
import org.learn365.modal.subscription.entity.OfferedGrade;
import org.learn365.modal.subscription.entity.OfferedSubject;
import org.learn365.modal.subscription.entity.SubscriptionPlan;
import org.learn365.modal.subscription.request.*;
import org.learn365.modal.subscription.response.OfferedGradeResponse;
import org.learn365.modal.subscription.response.OfferedPlanResponse;
import org.learn365.modal.subscription.response.OfferedSubjectResponse;
import org.learn365.modal.subscription.response.SubscriptionDetailsResponse;
import org.learn365.subscription.repository.OfferedGradeRepository;
import org.learn365.subscription.repository.SubscriptionPlanRepository;
import org.learn365.subscription.service.SubscriptionPortfolioService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class SubscriptionPortfolioServiceImpl implements SubscriptionPortfolioService {

    private OfferedGradeRepository offeredGradeRepository;

    public SubscriptionPortfolioServiceImpl(
            OfferedGradeRepository offeredGradeRepository) {
        this.offeredGradeRepository = offeredGradeRepository;
    }

    @Transactional
    @Override
    public OfferedGradeResponse createSubscriptionPortFolio(
            OfferedGradeRequest offeredGradeRequest) {
        if (ObjectUtils.isEmpty(offeredGradeRequest)) {
            throw new IllegalArgumentException("Subscription should not be null");
        }
        /**
         * transform Request object to entity object
         */
        OfferedGrade offerGrade = new OfferedGrade();
        BeanUtils.copyProperties(offeredGradeRequest, offerGrade);

        offerGrade.setOfferedPlan(mapRequestToSubscriptionPlanEntity(offeredGradeRequest.getOfferedPlan(),offerGrade));

        /**
         * Save all the object
         */
        OfferedGrade dbOfferGrade=null;

        try {
            dbOfferGrade=offeredGradeRepository.save(offerGrade);
        } catch (Exception exception) {
            new SubscriptionCoursePortFolioException(exception.getMessage());
        }

        if (ObjectUtils.isEmpty(dbOfferGrade)) {
            throw new SubscriptionCoursePortFolioException("Unable to save subscription portfolio for Learn365");
        }

        OfferedGradeResponse gradeResponse=new OfferedGradeResponse();
        BeanUtils.copyProperties(dbOfferGrade,gradeResponse);
        gradeResponse.setOfferedPlan(mapEntityToSubscriptionPlanResponse(dbOfferGrade.getOfferedPlan()));
        return gradeResponse;
    }

    private List<SubscriptionPlan> mapRequestToSubscriptionPlanEntity(List<OfferedPlanRequest> offeredPlan,OfferedGrade offerGrade){
        return offeredPlan.stream().filter(plan->plan!=null).map(offerPlan->{
            SubscriptionPlan subscriptionPlan=new SubscriptionPlan();
            subscriptionPlan.setActive(true);
            subscriptionPlan.setOfferGrade(offerGrade);
            BeanUtils.copyProperties(offerPlan,subscriptionPlan);
            subscriptionPlan.setCourseSubscriptionDetail(mapRequestToOfferedSubject(offerPlan.getCourseSubscriptionDetail(),subscriptionPlan));
            return subscriptionPlan;
        }).collect(Collectors.toList());
    }

    private List<OfferedSubject> mapRequestToOfferedSubject(List<OfferedSubjectRequest> offeredSubjectRequest, SubscriptionPlan subscriptionPlan){
        return offeredSubjectRequest.stream().filter(subject->subject!=null).map(offeredSubjectreq->{
            OfferedSubject offerdSubject=new OfferedSubject();
            offerdSubject.setActive(true);
            offerdSubject.setSubscriptionPlan(subscriptionPlan);
            BeanUtils.copyProperties(offeredSubjectreq,offerdSubject);
            return offerdSubject;
        }).collect(Collectors.toList());
    }

    private List<OfferedPlanResponse> mapEntityToSubscriptionPlanResponse(List<SubscriptionPlan> offeredPlan){
        return offeredPlan.stream().filter(plan->plan!=null && plan.getActive()==true).map(offerPlan->{
            OfferedPlanResponse subscriptionPlan=new OfferedPlanResponse();
            BeanUtils.copyProperties(offerPlan,subscriptionPlan);
            subscriptionPlan.setOfferedSubject(mapEntityToOfferedSubjectResponse(offerPlan.getCourseSubscriptionDetail()));
            return subscriptionPlan;
        }).collect(Collectors.toList());
    }

    private List<OfferedSubjectResponse> mapEntityToOfferedSubjectResponse(List<OfferedSubject> offeredSubject){
        return offeredSubject.stream().filter(subject->subject!=null && subject.getActive()==true).map(offerSubject->{
            OfferedSubjectResponse offerdSubject=new OfferedSubjectResponse();
            BeanUtils.copyProperties(offerSubject,offerdSubject);
            return offerdSubject;
        }).collect(Collectors.toList());
    }


    private List<OfferedSubject> mapSubscriptionDetailsRequest(List<SubscriptionDetailsRequest> subscriptionDetailRequest, SubscriptionPlan subscriptionPlan) {
        return subscriptionDetailRequest.stream().filter(subdetails -> subdetails != null).map(subscriptionDetails -> {
            OfferedSubject subsdetail = new OfferedSubject();
            subsdetail.setSubscriptionPlan(subscriptionPlan);
            BeanUtils.copyProperties(subscriptionDetails, subsdetail);
            return subsdetail;
        }).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public OfferedGradeResponse updateSubscriptionPortFolio(
            OfferedGradeRequest offeredGradeRequest, Long pubId) {
        if (pubId == null || ObjectUtils.isEmpty(offeredGradeRequest)) {
            throw new IllegalArgumentException("Request is not valid");
        }

  /*      OfferedSubject course = new OfferedSubject();
        course.setUpdatedAt(LocalDate.now());
        BeanUtils.copyProperties(offeredGradeRequest, course);
		course.setId(subid);

		Optional<SubscriptionDetails> coursesubscriptionPortfolio = Optional.empty();
		try {
			coursesubscriptionPortfolio = Optional.of(coureseCoursesubscriptionPortfolioRepository.save(course));
		} catch (Exception exception) {
			new SubscriptionCoursePortFolioException(exception.getMessage());
		}

		coursesubscriptionPortfolio.orElseThrow(
				() -> new SubscriptionCoursePortFolioException("Unable to update the subscrirption details"));
		SubscriptionPortfolioResponse subscriptionPortfolioResponse = new SubscriptionPortfolioResponse();
		BeanUtils.copyProperties(coursesubscriptionPortfolio.get(), subscriptionPortfolioResponse);*/
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferedGradeResponse> getAllSubscriptionPortFolio() {
        List<OfferedGrade> offerGrade = offeredGradeRepository.findAll();

        if (CollectionUtils.isEmpty(offerGrade)) {
            throw new SubscriptionCoursePortFolioException("No data available for subscription. Please contact admin");
        }
        return offerGrade.stream().filter(offergrade -> offergrade != null && offergrade.getActive()).map(dboffergrade -> {
            OfferedGradeResponse offeredGradeResponse = new OfferedGradeResponse();
            BeanUtils.copyProperties(dboffergrade, offeredGradeResponse);
            offeredGradeResponse.setOfferedPlan(mapEntityToSubscriptionPlanResponse(dboffergrade.getOfferedPlan()));
            return offeredGradeResponse;
        }).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    @Override
    public OfferedGradeResponse getBygradeIdSubscriptionPortFolio(Long gradeId) {
        if (gradeId == null) {
            throw new IllegalArgumentException("Request is invalid as is is not available");
        }

        OfferedGrade offerGrade = offeredGradeRepository.findById(gradeId)
                .orElseThrow(() -> new SubscriptionCoursePortFolioException("No subscription grade is available for grade" + gradeId));
        OfferedGradeResponse offeredGraderesponse = new OfferedGradeResponse();
        BeanUtils.copyProperties(offerGrade, offeredGraderesponse);
        //offeredGraderesponse.setOfferedPlan(mapEntityToSubscriptionPlanResponse(offerGrade.getOfferedPlan()));
        return offeredGraderesponse;
    }

    @Transactional
    @Override
    public Boolean deleteOfferedGrade(Long gradId) {

        if (gradId == null) {
            throw new IllegalArgumentException("Request is invalid as is is not available");
        }
        Optional<Boolean> subscriptionPlan = Optional.empty();
        try {
            subscriptionPlan= offeredGradeRepository.deleteByGradeId(gradId);
        } catch (Exception exception) {
            throw new SubscriptionCoursePortFolioException(exception.getMessage());
        }
        return subscriptionPlan.get();
    }

    private List<SubscriptionDetailsResponse> mapSubscriptionDetailsResponse(List<OfferedSubject> subscriptionDetail) {
        return subscriptionDetail.stream().filter(subdetails -> subdetails != null).map(subscriptionDetails -> {
            SubscriptionDetailsResponse subsdetailResponse = new SubscriptionDetailsResponse();
            BeanUtils.copyProperties(subscriptionDetails, subsdetailResponse);
            return subsdetailResponse;
        }).collect(Collectors.toList());
    }

}

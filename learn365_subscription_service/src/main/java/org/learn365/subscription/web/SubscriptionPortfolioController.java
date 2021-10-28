package org.learn365.subscription.web;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.learn365.modal.subscription.request.OfferedGradeRequest;
import org.learn365.modal.subscription.request.OfferedPlanRequest;
import org.learn365.modal.subscription.request.SubscriptionPlanRequest;
import org.learn365.modal.subscription.response.OfferedGradeResponse;
import org.learn365.subscription.service.SubscriptionPortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("This provide all subscription related operation and give user to variety of option to subscribe and start there learning journeyAPI ")
@RestController
@RequestMapping("v1/subscriptionportfolio/")
@Validated
public class SubscriptionPortfolioController
{
    private static Logger log = LoggerFactory.getLogger(
            SubscriptionPortfolioController.class);

    private SubscriptionPortfolioService subscriptionPortfolio;

    public SubscriptionPortfolioController(
            SubscriptionPortfolioService subscriptionPortfolio)
    {
        this.subscriptionPortfolio = subscriptionPortfolio;
    }

    @ApiOperation(
            value = "This API basically facilitate the admin to create different subscription portfolio that they want to give to their user for subscription")
    @PostMapping(value = {"create", "service/create"},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OfferedGradeResponse createSubscriptionPortFolio(
            @Valid @RequestBody OfferedGradeRequest gradeRequest)
    {
        log.info("Request to create subscription plan for grade : {}",
                gradeRequest.getGradeName());
        return subscriptionPortfolio.createSubscriptionPortFolio(gradeRequest);

    }

    @PutMapping(value = {"{gradeId}", "service/{gradeId}"},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OfferedGradeResponse updateSubscriptionPortFolio(
            @RequestBody OfferedGradeRequest offerGradeRequest,
            @PathVariable(value = "gradeId", required = true) Long gradeId)
    {
        log.info("Request to update subscription plan for grade : {}",
                gradeId);
        return subscriptionPortfolio.updateSubscriptionPortFolio(
                offerGradeRequest, gradeId);

    }

    @ApiOperation(
            "Api is used to fetch all the available Subscription in Learn365 portfolio and give user option to " +
                    "subscribe one or more grade")
    @GetMapping(
            value = {"getAllOfferedSubscription", "service/getAllOfferedSubscription"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfferedGradeResponse> getAllSubscriptionPortFolio()
    {
        log.info("Request to get all subscription plan ");
        return subscriptionPortfolio.getAllSubscriptionPortFolio();

    }

    @GetMapping(value = {"{gradeName}", "service/{gradeName}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OfferedGradeResponse getByIdSubscriptionPortFolio(
            @PathVariable(value = "gradeName",
                    required = true) String gradeName)
    {
        log.info("Request to get all subscription usinf grade name: {} ",gradeName);
        OfferedGradeResponse offeredGradeResponse = subscriptionPortfolio.getBygradeIdSubscriptionPortFolio(
                gradeName);
        return offeredGradeResponse;
    }

    @DeleteMapping(value = {"{gradeId}", "service/{gradeId}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteIdSubscriptionPortFolio(
            @PathVariable(value = "gradeId", required = true) Long gradeid)
    {
        String loggedInUser= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("Request to delete subscription using grade: {} by user : {}",gradeid,loggedInUser);
        return subscriptionPortfolio.deleteOfferedGrade(gradeid);

    }

}

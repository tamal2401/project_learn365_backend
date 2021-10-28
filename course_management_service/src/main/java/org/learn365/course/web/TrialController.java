package org.learn365.course.web;

import org.learn365.course.service.UserTrialDetailsService;
import org.learn365.modal.course.request.TrialPeriodRequest;
import org.learn365.modal.course.response.Recentvideo;
import org.learn365.modal.course.response.TrialPeriodResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/trial")
@Validated
public class TrialController
{

    private Logger log = LoggerFactory.getLogger(TrialController.class);

    private UserTrialDetailsService userTrialDetailsService;

    public TrialController(UserTrialDetailsService userTrialDetailsService)
    {
        this.userTrialDetailsService = userTrialDetailsService;

    }

    @PostMapping(value = {"/create", "service/create"})
    public TrialPeriodResponse createUserTrial(
            @Valid @RequestBody TrialPeriodRequest trialPeriodRequest)
    {
        log.info("Request for trial subscription for user : {}",trialPeriodRequest.getUserid());
        return userTrialDetailsService.createTrialData(trialPeriodRequest);
    }

    @GetMapping(value = {"/find/{userid}", "service/find/{userid}"})
    public TrialPeriodResponse createUserTrial(
            @PathVariable(value = "userid", required = true) Long userId)
    {
        log.info("Request to get trial subscription data for user : {}",userId);
        return userTrialDetailsService.getTrailStatus(userId);
    }

    @GetMapping(value = {"/trialvideo/{grade}", "service/trialvideo/{grade}"})
    public List<Recentvideo> getTrialVideo(
            @PathVariable(value = "grade", required = true) String gradename)
    {
        log.info("Request to get trial videos for grade : {}",gradename);
        return userTrialDetailsService.getTrialVideos(gradename);
    }
}

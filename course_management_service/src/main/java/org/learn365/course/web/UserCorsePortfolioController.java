package org.learn365.course.web;

import org.learn365.course.service.StudentCourseProfileService;
import org.learn365.course.service.TrackingProgressService;
import org.learn365.course.service.UserCoursePortfolioService;
import org.learn365.modal.course.request.TrackStudentProgressRequest;
import org.learn365.modal.course.request.UserCoursePortfolioRequest;
import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.course.response.SubscribedSubjectReport;
import org.learn365.modal.course.response.UserCoursePortfolioResponse;
import org.learn365.modal.course.response.UserVideos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserCorsePortfolioController
{
    private Logger log = LoggerFactory.getLogger(
            UserCorsePortfolioController.class);

    private UserCoursePortfolioService userCoursePortfolioService;
    private StudentCourseProfileService studentCourseProfileService;
    private TrackingProgressService trackingProgressService;

    public UserCorsePortfolioController(
            UserCoursePortfolioService userCoursePortfolioService,
            StudentCourseProfileService studentCourseProfileService,
            TrackingProgressService trackingProgressService)
    {
        this.userCoursePortfolioService = userCoursePortfolioService;
        this.studentCourseProfileService = studentCourseProfileService;
        this.trackingProgressService = trackingProgressService;
    }

    @PostMapping(value = {"/courseadd", "/service/courseadd"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserCoursePortfolioResponse addUserCoursePortfolio(
            @Valid @RequestBody UserCoursePortfolioRequest userCoursePortfolio)
    {
        log.info("Request for course protfolio creation for grade : {}",
                userCoursePortfolio.getGradename());
        return userCoursePortfolioService.addUserCoursePortfolio(
                userCoursePortfolio);
    }

    @GetMapping(
            value = {"/getUserCousePortfolio", "/service/getUserCousePortfolio"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StandardResponse> getUserCousePortfolio(
            @RequestParam(name = "userId", required = true) Long userId)
    {
        log.info("Request for get user course portfolio for user  : {}",
                userId);
        return studentCourseProfileService.getUserCourseDetails(userId);
    }

    @PutMapping(value = {"/updateprogress", "/service/updateprogress"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateUserCousePortfolioprogress(
            @Valid @RequestBody TrackStudentProgressRequest trackStudentProgressRequest)
    {
        log.info(
                "Request to update user progress for user  : {} and for table : {}",
                trackStudentProgressRequest.getUserId(),
                trackStudentProgressRequest.getTableName().toString());
        return trackingProgressService.updateProgress(
                trackStudentProgressRequest);
    }


    @GetMapping(value = {"/subscribedsubject", "/service/subscribedsubject"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubscribedSubjectReport> getSubscribedSubjectList(
            @RequestParam(name = "userid", required = true) Long userId,
            @RequestParam(name = "gradeName", required = true) String gradename)
    {
        log.info(
                "Request to get subscribed Subject for user  : {} and for grade : {}",
                userId, gradename);
        return trackingProgressService.getSubscribedSubject(userId, gradename);
    }

    @GetMapping(value = {"/recentvideos", "/service/recentvideos"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserVideos getSubscribedSubjectList(
            @RequestParam(name = "userid", required = true) Long userId)
    {
        log.info(
                "Request to get recent videos for user  : {}", userId);
        return trackingProgressService.getRecentVideos(userId);
    }


}

package org.learn365.course.web;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.learn365.course.service.StudentCourseProfileService;
import org.learn365.course.service.UserCoursePortfolioService;
import org.learn365.modal.course.request.UserCoursePortfolioRequest;
import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.course.response.UserCoursePortfolioResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
public class UserCorsePortfolioController {

    private UserCoursePortfolioService userCoursePortfolioService;
    private StudentCourseProfileService studentCourseProfileService;

    public UserCorsePortfolioController(UserCoursePortfolioService userCoursePortfolioService, StudentCourseProfileService studentCourseProfileService) {
        this.userCoursePortfolioService = userCoursePortfolioService;
        this.studentCourseProfileService=studentCourseProfileService;
    }

    @PostMapping(value ={"/courseadd","/service/courseadd"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserCoursePortfolioResponse addUserCoursePortfolio(
            @Valid @RequestBody UserCoursePortfolioRequest userCoursePortfolio) {
        return userCoursePortfolioService.addUserCoursePortfolio(userCoursePortfolio);
    }

/*    @GetMapping(name = "getuserCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserCoursePortfolioResponse> getUserCoursePortfolioByGrade(
            @RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "grade", required = true) String grade) {
        return Collections.singletonList(new UserCoursePortfolioResponse());
    }*/

    @GetMapping(value = {"/getUserCousePortfolio","/service/getUserCousePortfolio"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StandardResponse> getUserCousePortfolio(
            @RequestParam(name = "userId", required = true) Long userId) {
        return studentCourseProfileService.getUserCourseDetails(userId);
    }


}

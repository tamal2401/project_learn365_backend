package org.learn365.course.web;

import org.learn365.course.service.UserCourseService;
import org.learn365.modal.course.request.UserInprogressCourseRequest;
import org.learn365.modal.course.response.UserInprogressCourseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/user/")
public class UserCourseController
{
	private static Logger log = LoggerFactory.getLogger(UserCourseController.class);
    private UserCourseService userCourseService;

    public UserCourseController(UserCourseService userCourseService)
    {
        this.userCourseService = userCourseService;
    }

    @PostMapping(value = "userinprogresscourse",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUserInprogressCourse(
            @Valid @RequestBody UserInprogressCourseRequest userInprogressCourseRequest)
    {
		log.info("Request to save user inprogress course");
        userCourseService.addUserInprogressCourse(userInprogressCourseRequest);
    }

    @GetMapping(value = "userinprogresscourse/{userid}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInprogressCourseResponse getUserInprogressCourseByUser(
            @PathVariable(name = "userid") String userid)
    {
		log.info("Request to get user inprogress course");
        return userCourseService.getUserInprogressCourseByUser(userid);
    }


}

package org.learn365.user.web;

import javax.validation.Valid;

import org.learn365.modal.common.Mail;
import org.learn365.modal.user.UserData;
import org.learn365.modal.user.UserRegisterStatus;
import org.learn365.modal.user.UserRequest;
import org.learn365.user.service.UserService;
import org.learn365.user.service.impl.UserServiceImpl;
import org.learn365.user.utility.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
@Validated
public class UserController
{

    private static Logger log = LoggerFactory.getLogger(
            UserController.class);

    private UserService userService;
    private EmailUtil emailUtil;

    public UserController(UserService userService, EmailUtil emailUtil)
    {
        this.userService = userService;
        this.emailUtil = emailUtil;
    }

    @PostMapping(value = {"register",
            "service/register"}, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRegisterStatus registerUser(
            @Valid @RequestBody UserRequest userRequest)
    {
        log.info("Request received to register user");
        return userService.registerUser(userRequest);
    }

    @PutMapping(value = {"update/{userid}",
            "service/update/{userid}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData updateUser(@Valid @RequestBody UserRequest userRequest,
                               @PathVariable(name = "userid",
                                       required = true) Long userId)
    {
        log.info("Request received to update user");
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping(value = {"delete/{userid}", "service/delete/{userid}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUser(
            @PathVariable(name = "userid", required = true) Long userId)
    {
        log.info("Request received to delete user");
        return userService.deleteUser(userId);
    }

    @GetMapping(value = {"fetch/{userid}", "service/fetch/{userid}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData getUserDetails(
            @PathVariable(name = "userid", required = true) Long userId)
    {
        log.info("Request received to fetch user");
        return userService.fetchUser(userId);
    }

    @GetMapping(value = {"get/{email}", "service/fetch/{email}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData getUserDetailsByEmailId(
            @PathVariable(name = "email", required = true) String email)
    {
        log.info("Request received to get user by email");
        return userService.fetchUserByEmailId(email);
    }


}

package org.learn365.user.web;

import org.learn365.modal.common.MailQueryDetails;
import org.learn365.modal.user.UserData;
import org.learn365.modal.user.entity.UserOtp;
import org.learn365.user.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/")
public class OtpController
{
    private static Logger log = LoggerFactory.getLogger(
            OtpController.class);
    private OtpService otpService;

    OtpController(OtpService otpService)
    {
        this.otpService = otpService;
    }

    @PostMapping(
            value = {"otp/regenrateOtp/{email}", "service/otp/regenrateOtp/{email}"})
    public Boolean sendOtp(
            @PathVariable(value = "email", required = true) String email)
    {
        log.info("Request received to generate Otp");
        boolean isOtpProcessed = false;
        UserOtp userotp = new UserOtp();
        userotp.setEmailid(email);
        UserOtp processedOtp = otpService.addUserOtp(userotp);
        if (null != processedOtp && processedOtp.getOtpid() != null)
        {
            isOtpProcessed = true;
        }
        return isOtpProcessed;
    }

    @GetMapping(
            value = {"otp/verify/{email}", "service/otp/verify/{email}"})
    public UserData verifyOtp(
            @PathVariable(value = "email", required = true) String email,
            @RequestParam(value = "otp", required = true) String otp)
    {
        log.info("Request received to verify Otp");
        return otpService.verifyOtp(email, otp);
    }

    @PostMapping(value = {"/question", "/service/question"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendEmail(@Valid @RequestBody MailQueryDetails mailDetails)
            throws Exception
    {
        log.info("Request received to get user by email");
        return otpService.helpAndSupport(mailDetails);
    }
}

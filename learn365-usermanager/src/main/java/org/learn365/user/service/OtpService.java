package org.learn365.user.service;

import org.learn365.modal.common.Mail;
import org.learn365.modal.common.MailQueryDetails;
import org.learn365.modal.user.UserData;
import org.learn365.modal.user.entity.UserOtp;

import java.io.IOException;

public interface OtpService
{
    UserOtp addUserOtp(UserOtp userotp);

    UserData verifyOtp(String emailid, String otp);

    Boolean helpAndSupport(MailQueryDetails mailDetails) throws Exception;
}

package org.learn365.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.learn365.exception.OtpException;
import org.learn365.modal.common.Mail;
import org.learn365.modal.common.MailQueryDetails;
import org.learn365.modal.common.MailTemplate;
import org.learn365.modal.common.UserQueryDetails;
import org.learn365.modal.user.UserData;
import org.learn365.modal.user.entity.Learn365User;
import org.learn365.modal.user.entity.UserOtp;
import org.learn365.user.config.EmailConfiguration;
import org.learn365.user.repository.Learn365UserRepository;
import org.learn365.user.repository.UserOTPRepository;
import org.learn365.user.service.OtpService;
import org.learn365.user.utility.EmailUtil;
import org.learn365.user.utility.GenerateOTP;
import org.learn365.user.utility.TemplateManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService
{


    private static Logger log = LoggerFactory.getLogger(
            OtpServiceImpl.class);
    @Value("${learn365.otp.validity}")
    private Integer otpvalidity;
    private EmailConfiguration emailConfiguration;
    private UserOTPRepository userOTPRepository;
    private GenerateOTP generateOTP;
    private Learn365UserRepository learn365UserRepository;
    private EmailUtil email;
    private TemplateManagement template;

    public OtpServiceImpl(UserOTPRepository userOTPRepository,
                          GenerateOTP generateOTP,
                          Learn365UserRepository learn365UserRepository,
                          EmailUtil email,
                          EmailConfiguration emailConfiguration,
                          TemplateManagement template)
    {
        this.userOTPRepository = userOTPRepository;
        this.generateOTP = generateOTP;
        this.learn365UserRepository = learn365UserRepository;
        this.email = email;
        this.emailConfiguration = emailConfiguration;
        this.template = template;
    }

    @Override
    public UserOtp addUserOtp(UserOtp userotp)
    {
        if (null != userotp && StringUtils.isEmpty(userotp.getEmailid()))
        {
            log.info("Email id should never be null to generate otp");
            throw new IllegalArgumentException("Email should never be null");
        }

        Optional<Learn365User> user = learn365UserRepository.findByEmail(
                userotp.getEmailid());

        user.orElseThrow(() -> new OtpException("Not a valid user to get otp"));
        userotp.setOtp(
                generateOTP.generate("" + System.currentTimeMillis()));
        LocalDateTime receiveddate = LocalDateTime.now();
        userotp.setReceiveddate(receiveddate);
        userotp.setExpireddate(receiveddate.plusMinutes(otpvalidity));
        UserOtp saveduserotp = null;
        try
        {
            saveduserotp = userOTPRepository.save(userotp);

        } catch (Exception e)
        {
            log.info("Unable to save Otp for user");
            throw new OtpException("Unable to save Otp for user");
        }
        try
        {
            email.sendEmail(createMail(saveduserotp.getEmailid(),
                    saveduserotp.getOtp()));
        } catch (Exception e)
        {
            log.info("Exception in sending Email to user");
            throw new OtpException("Exception in sending Email to user");
        }
        return saveduserotp;
    }

    @Override
    public UserData verifyOtp(String emailid, String otp)
    {
        if (StringUtils.isEmpty(emailid) || StringUtils.isEmpty(otp))
        {
            log.info("Invalid request email Id is required to generate otp");
            throw new OtpException(
                    "Invalid request email Id is required to generate otp");
        }
        Pageable page = PageRequest.of(0, 1);
        Optional<UserOtp> isuserotp = Optional.empty();
        List<UserOtp> uotp = new ArrayList<>();
        try
        {
            uotp = userOTPRepository.findEmailid(emailid, page);
            isuserotp = Optional.of(uotp.get(0));
        } catch (Exception e)
        {
            log.info("Unable to find otp for user");
            throw new OtpException("Unable to find otp for user :" + emailid);
        }


        UserOtp userotp = isuserotp.orElseThrow(() ->
        {
            log.info("No Otp is available for this user");
            OtpException otpexception = new OtpException(
                    "No otp information is available for this user :" + emailid);
            return otpexception;
        });
        Optional<Learn365User> isuser = Optional.empty();
        if (isOtpActive(userotp.getOtp(), otp, userotp.getExpireddate()))
        {
            log.info("Otp is verified going to update user : {}", emailid);
            isuser = ActivateUser(emailid);
        }
        Learn365User learn365user = isuser.orElseThrow(
                () -> new OtpException("User is not present"));
        UserData userdata = new UserData();
        BeanUtils.copyProperties(learn365user, userdata);
        return userdata;
    }

    @Override
    public Boolean helpAndSupport(MailQueryDetails mailDetails) throws Exception
    {
        boolean isMailsent = false;

        Optional<Learn365User> user = learn365UserRepository.findByEmail(
                mailDetails.getUserMailId());

        user.orElseThrow(
                () -> new OtpException("Not a valid user to send mail"));

        Mail userMail = userTemplateResolver(mailDetails, user.get());
        isMailsent = sendMailAndConfirm(userMail);

        if (!isMailsent)
        {
            throw new Exception(String.format(
                    "Unable to send mail to user %s for %s",
                    user.get().getUserName(), mailDetails.getTemplateType()));
        }
        Mail supportMail = supportTemplateResolver(mailDetails, user.get());
        isMailsent = sendMailAndConfirm(supportMail);
        return isMailsent;
    }

    private Mail supportTemplateResolver(MailQueryDetails mailDetails,
                                         Learn365User learn365User)
            throws IOException
    {
        Mail mail = new Mail();

        if (MailTemplate.ASK_A_QUESTION.equals(mailDetails.getTemplateType()))
        {
            // send user
            UserQueryDetails userQuery = mailDetails.getQueryDetails();
            mail.setMailTo(learn365User.getEmail());
            mail.setMailFrom(emailConfiguration.getFrom());
            mail.setContentType("text/html");
            mail.setMailSubject(emailConfiguration.getRaiseAQuestionSubject());
            String askQuestionTemplate = template.ReadFile(null,
                    "ask_a_question_support.html");
            String messageBody = askQuestionTemplate
                    .replace("USER_NAME", learn365User.getUserName())
                    .replace("USER_GRADE", userQuery.getUserGrade())
                    .replace("USER_MOBILE", learn365User.getMobileNumber())
                    .replace("USER_EMAIL", learn365User.getEmail())
                    .replace("SUBJECT_NAME", userQuery.getSubjectName())
                    .replace("CHAPTER_NAME", userQuery.getChapterName())
                    .replace("TOPIC_NAME", userQuery.getTopicName())
                    .replace("USER_QUERY", userQuery.getUserQuery());
            mail.setMailContent(messageBody);
        } else if (MailTemplate.BOOK_A_LIVE_CLASS.equals(
                mailDetails.getTemplateType()))
        {
            UserQueryDetails userQuery = mailDetails.getQueryDetails();
            mail.setMailTo(emailConfiguration.getFrom());
            mail.setMailFrom(emailConfiguration.getFrom());
            mail.setContentType("text/html");
            mail.setMailSubject(emailConfiguration.getRaiseAQuestionSubject());
            String askQuestionTemplate = template.ReadFile(null,
                    "book_a_live_class_support.html");
            String messageBody = askQuestionTemplate
                    .replace("USER_NAME", learn365User.getUserName())
                    .replace("USER_GRADE", userQuery.getUserGrade())
                    .replace("USER_MOBILE", learn365User.getMobileNumber())
                    .replace("USER_EMAIL", learn365User.getEmail())
                    .replace("SUBJECT_NAME", userQuery.getSubjectName())
                    .replace("CHAPTER_NAME", userQuery.getChapterName())
                    .replace("TOPIC_NAME", userQuery.getTopicName())
                    .replace("BOOKED_DATE", mailDetails.getBookedClassDate());
            mail.setMailContent(messageBody);
        }
        return mail;
    }

    private boolean sendMailAndConfirm(Mail mail)
    {
        boolean isMailsent;
        try
        {
            email.sendEmail(mail);
            isMailsent = true;
        } catch (Exception exception)
        {
            log.info(
                    "Error in sending mail for ask a question or join online classes");
            throw new OtpException(
                    "Error in sending mail for ask a question or join online classes");
        }
        return isMailsent;
    }

    private Mail userTemplateResolver(MailQueryDetails mailDetails,
                                      Learn365User learn365User)
            throws IOException
    {
        Mail mail = new Mail();

        if (MailTemplate.ASK_A_QUESTION.equals(mailDetails.getTemplateType()))
        {
            // send user
            UserQueryDetails userQuery = mailDetails.getQueryDetails();
            mail.setMailTo(learn365User.getEmail());
            mail.setMailFrom(emailConfiguration.getFrom());
            mail.setContentType("text/html");
            mail.setMailSubject(emailConfiguration.getRaiseAQuestionSubject());
            String askQuestionTemplate = template.ReadFile(null,
                    "ask_a_question_user.html");
            String messageBody = askQuestionTemplate
                    .replace("USER_NAME", learn365User.getUserName())
                    .replace("SUBJECT_NAME", userQuery.getSubjectName())
                    .replace("CHAPTER_NAME", userQuery.getChapterName())
                    .replace("TOPIC_NAME", userQuery.getTopicName())
                    .replace("USER_QUERY", userQuery.getUserQuery());
            mail.setMailContent(messageBody);
        } else if (MailTemplate.BOOK_A_LIVE_CLASS.equals(
                mailDetails.getTemplateType()))
        {
            UserQueryDetails userQuery = mailDetails.getQueryDetails();
            mail.setMailTo(emailConfiguration.getFrom());
            mail.setMailFrom(emailConfiguration.getFrom());
            mail.setContentType("text/html");
            mail.setMailSubject(emailConfiguration.getRaiseAQuestionSubject());
            String askQuestionTemplate = template.ReadFile(null,
                    "book_a_live_class_user.html");
            String messageBody = askQuestionTemplate
                    .replace("USER_NAME", learn365User.getUserName())
                    .replace("SUBJECT_NAME", userQuery.getSubjectName())
                    .replace("CHAPTER_NAME", userQuery.getChapterName())
                    .replace("TOPIC_NAME", userQuery.getTopicName())
                    .replace("BOOKED_DATE", mailDetails.getBookedClassDate());
            mail.setMailContent(messageBody);
        }

        return mail;
    }

    private Optional<Learn365User> ActivateUser(String emailid)
    {
        Optional<Learn365User> isuser = Optional.empty();
        try
        {
            learn365UserRepository.activateUser(emailid);
            isuser = learn365UserRepository.findByEmail(emailid);
        } catch (Exception e)
        {
            log.info("unable to activate user : {} due to exception :{}",
                    emailid, e);
            throw new OtpException(
                    "unable to activate user due to exception " + e.getMessage());
        }
        return isuser;
    }

    private boolean isOtpActive(String savedOtp, String reqOtp,
                                LocalDateTime expitydate)
    {
        boolean isOtpValid = false;
        if (StringUtils.equals(savedOtp,
                reqOtp) && expitydate.isAfter(LocalDateTime.now()))
        {
            isOtpValid = true;
        }
        return isOtpValid;
    }

    private Mail createMail(String toMail, String otp) throws IOException
    {
        Mail mail = new Mail();
        mail.setMailTo(toMail);
        mail.setMailFrom(emailConfiguration.getFrom());
        mail.setContentType("text/html");
        mail.setMailSubject(emailConfiguration.getOtpSubject());
        String otpTemplate = template.ReadFile(null,
                "otp_message_template.html");
        String messageBody = otpTemplate.replace("EMAIL_ADD", "toMail")
                .replace("OTP_CODE", otp);
        mail.setMailContent(messageBody);
        return mail;
    }
}

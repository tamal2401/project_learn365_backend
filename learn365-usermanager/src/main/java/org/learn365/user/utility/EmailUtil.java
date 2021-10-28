package org.learn365.user.utility;

import org.learn365.modal.common.Mail;
import org.learn365.user.config.EmailConfiguration;
import org.learn365.user.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class EmailUtil
{
    private static Logger log = LoggerFactory.getLogger(
            EmailUtil.class);
    @Autowired
    JavaMailSender mailSender;
    private EmailConfiguration emailconfiguration;

    public EmailUtil(EmailConfiguration emailconfiguration)
    {
        this.emailconfiguration = emailconfiguration;
    }


    public boolean sendEmail(Mail mail) throws UnsupportedEncodingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try
        {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(),
                    "learn365.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return true;
    }


    private Properties getProperties()
    {
        Properties awsmailprop = new Properties();
        awsmailprop.put("mail.transport.protocol", "smtps");
        awsmailprop.put("mail.smtp.port", emailconfiguration.getPort());
        awsmailprop.put("mail.smtp.auth", "true");
        awsmailprop.put("mail.smtp.starttls.enable", "true");
        awsmailprop.put("mail.smtp.starttls.required", "true");
        awsmailprop.put("mail.smtp.host", emailconfiguration.getHost());
        return awsmailprop;
    }
}

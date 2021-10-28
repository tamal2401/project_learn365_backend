package org.learn365.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfiguration {
    private String host;
    private String port;
    private String username;
    private String password;
    public String from;
    public String otpSubject;
    private String bookAClassSubject;
    private String raiseAQuestionSubject;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBookAClassSubject() {
        return bookAClassSubject;
    }

    public void setBookAClassSubject(String bookAClassSubject) {
        this.bookAClassSubject = bookAClassSubject;
    }

    public String getRaiseAQuestionSubject() {
        return raiseAQuestionSubject;
    }

    public void setRaiseAQuestionSubject(String raiseAQuestionSubject) {
        this.raiseAQuestionSubject = raiseAQuestionSubject;
    }

    public String getOtpSubject() {
        return otpSubject;
    }

    public void setOtpSubject(String otpSubject) {
        this.otpSubject = otpSubject;
    }
}

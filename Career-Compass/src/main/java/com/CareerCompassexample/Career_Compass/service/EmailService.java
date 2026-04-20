package com.CareerCompassexample.Career_Compass.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // Spring Boot will inject JavaMailSender automatically
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a plain text reminder email
     *
     * @param to - receiver email
     * @param subject - email subject
     * @param body - email body text
     */
    public void sendReminderEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);             // recipient
        message.setSubject(subject);   // subject line
        message.setText(body);         // body text
        mailSender.send(message);      // send mail
    }
}

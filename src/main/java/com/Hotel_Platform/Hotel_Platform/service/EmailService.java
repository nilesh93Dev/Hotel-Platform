package com.Hotel_Platform.Hotel_Platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationLink(String toEmail, String token) {
        String subject = "Tenant Verification Required";
        String link = "https://hotel-platform.onrender.com/Hotel/tenantmaster/verify?token=" + token;
        //String link = "http://localhost:8080/Hotel/tenantmaster/verify?token=" + token;
        String body = "Click the link to verify tenant creation: " + link;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}

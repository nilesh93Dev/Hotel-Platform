package com.Hotel_Platform.Hotel_Platform.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    public void sendVerificationLink(String toEmail, String token) {
        try {
            String subject = "Tenant Verification Required";
            String link = "https://hotel-platform.onrender.com/Hotel/tenantmaster/verify?token=" + token;
            String body = "Click the link to verify tenant creation: " + link;

            Email from = new Email("nileshkumarpatna93@gmail.com");
            Email to = new Email(toEmail);
            Content content = new Content("text/plain", body);

            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println("Status Code: " + response.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

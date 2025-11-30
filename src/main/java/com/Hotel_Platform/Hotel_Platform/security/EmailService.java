package com.Hotel_Platform.Hotel_Platform.security;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	
//	@Autowired
//    private JavaMailSender mailSender;
//
//	public void sendOtpEmail(String toEmail, String otp) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(toEmail);
//            message.setSubject("Your OTP for Login");
//            message.setText("Your OTP is: " + otp + "\nThis is valid for 5 minutes.");
//            mailSender.send(message);
//
//            System.out.println("OTP email sent successfully to: " + toEmail);
//        } catch (Exception e) {
//            System.err.println("Failed to send OTP email to " + toEmail);
//            e.printStackTrace();  // <-- This will tell you exactly why it failed
//        }
//    }
	
	


	    public void sendOtpEmail(String fromEmail, String appPassword, String toEmail, String otp) {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername(fromEmail);
	        mailSender.setPassword(appPassword);

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        try {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setTo(toEmail);
	            message.setFrom(fromEmail);  // Optional: sets sender
	            message.setSubject("Your OTP for Login");
	            message.setText("Your OTP is: " + otp + "\nThis is valid for 5 minutes.");
	            mailSender.send(message);

	            System.out.println("OTP email sent successfully from: " + fromEmail);
	        } catch (Exception e) {
	            System.err.println("❌ Failed to send OTP email from: " + fromEmail);
	            e.printStackTrace();
	            throw new RuntimeException("Failed to send email: " + e.getMessage());
	        }
	    }
}



package com.finalyear.VolunteeringSystm.util.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Sent...");
    }

    public void sendWelcomeEmail(String toEmail, String newPassword) {
        String subject = "Welcome to Our Volunteering Program";
        String body = String.format(
                "Dear Volunteer,%n%nYour role has been updated to VOLUNTEER. Your new login password is: %s%n%nPlease log in and change your password.%n%nBest regards,%nVolunteering System Team",
                newPassword);
        sendSimpleEmail(toEmail, subject, body);
    }
}

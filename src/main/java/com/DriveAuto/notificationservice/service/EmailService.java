package com.DriveAuto.notificationservice.service;

import com.DriveAuto.notificationservice.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailDetails emailDetails) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("DRIVE-AUTO");
        helper.setTo(emailDetails.getRecipient());
        helper.setSubject(emailDetails.getSubject());
        helper.setText(emailDetails.getMessage(), true);

        mailSender.send(message);
    }
}

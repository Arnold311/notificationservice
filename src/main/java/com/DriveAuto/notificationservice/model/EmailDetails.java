package com.DriveAuto.notificationservice.model;


import lombok.Data;

@Data
public class EmailDetails {
    private String recipient;
    private String subject;
    private String message;

    public EmailDetails() {
    }

    public EmailDetails(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }
}
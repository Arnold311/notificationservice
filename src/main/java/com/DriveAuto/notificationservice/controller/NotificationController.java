package com.DriveAuto.notificationservice.controller;

import com.DriveAuto.notificationservice.model.EmailDetails;
import com.DriveAuto.notificationservice.model.Notification;
import com.DriveAuto.notificationservice.service.EmailService;
import com.DriveAuto.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {


    private EmailService emailService;


    private NotificationService notificationService;

    public NotificationController(EmailService emailService, NotificationService notificationService) {
        this.emailService = emailService;
        this.notificationService = notificationService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDetails emailDetails) {
        try {
            emailService.sendEmail(emailDetails);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email");
        }
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @RequestParam Long utilisateurId,
            @RequestParam String message) {
        Notification savedNotification = notificationService.saveNotification(utilisateurId, message);
        return ResponseEntity.ok(savedNotification);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{utilisateurId}")
    public ResponseEntity<List<Notification>> getNotificationsByUtilisateurId(@PathVariable Long utilisateurId) {
        List<Notification> notifications = notificationService.getNotificationsByUtilisateurId(utilisateurId);
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}

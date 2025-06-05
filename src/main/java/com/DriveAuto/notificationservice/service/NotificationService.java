package com.DriveAuto.notificationservice.service;

import com.DriveAuto.notificationservice.model.Notification;
import com.DriveAuto.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {


    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification saveNotification(Long utilisateurId, String message) {
        Notification notification = new Notification();
        notification.setUtilisateurId(utilisateurId);
        notification.setMessage(message);
        notification.setDate(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public  Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> getNotificationsByUtilisateurId(Long utilisateurId) {
        return notificationRepository.findByUtilisateurId(utilisateurId);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}

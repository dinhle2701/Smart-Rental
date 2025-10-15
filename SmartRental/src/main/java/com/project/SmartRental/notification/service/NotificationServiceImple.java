package com.project.SmartRental.notification.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.notification.dto.req.NotificationReq;
import com.project.SmartRental.notification.dto.res.NotificationRes;
import com.project.SmartRental.notification.model.Notification;
import com.project.SmartRental.notification.repository.NotificationRepository;

@Service
public class NotificationServiceImple implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Page<NotificationRes> getAllNoti(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notification -> NotificationRes.builder()
                .id(notification.getId())
                .notificationName(notification.getNotificationName())
                .notificationType(notification.getNotificationType())
                .description(notification.getDescription())
                .status(notification.getStatus())
                .createAt(notification.getCreateAt())
                .build());
    }

    @Override
    public Optional<NotificationRes> getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .map(notification -> NotificationRes.builder()
                .id(notification.getId())
                .notificationName(notification.getNotificationName())
                .notificationType(notification.getNotificationType())
                .description(notification.getDescription())
                .status(notification.getStatus())
                .createAt(notification.getCreateAt())
                .build());
    }

    @Override
    public NotificationRes createNotification(NotificationReq notificationReq) {
        Notification notification = new Notification();

        notification.setNotificationName(notificationReq.getNotificationName());
        notification.setNotificationType(notificationReq.getNotificationType());
        notification.setDescription(notificationReq.getDescription());
        notification.setCreateAt(LocalDateTime.now());

        Notification created = notificationRepository.save(notification);
        return NotificationRes.builder()
                .id(created.getId())
                .notificationName(created.getNotificationName())
                .notificationType(created.getNotificationType())
                .description(created.getDescription())
                .status(created.getStatus())
                .createAt(created.getCreateAt())
                .build();
    }

    @Override
    public NotificationRes updateNotification(Long id, NotificationReq notificationReq) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Notification not found with id: " + id,
                        "/api/v1/notification/" + id
                )
        );

        if (notificationReq.getNotificationName() != null
                && !notificationReq.getNotificationName().equals(notification.getNotificationName())) {
            notification.setNotificationName(notificationReq.getNotificationName());
        }

        if (notificationReq.getNotificationType() != null
                && !notificationReq.getNotificationType().equals(notification.getNotificationType())) {
            notification.setNotificationType(notificationReq.getNotificationType());
        }

        if (notificationReq.getDescription() != null
                && !notificationReq.getDescription().equals(notification.getDescription())) {
            notification.setDescription(notificationReq.getDescription());
        }

        Notification updated = notificationRepository.save(notification);

        return NotificationRes.builder()
                .id(updated.getId())
                .notificationName(updated.getNotificationName())
                .notificationType(updated.getNotificationType())
                .description(updated.getDescription())
                .status(updated.getStatus())
                .createAt(updated.getCreateAt())
                .build();
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}

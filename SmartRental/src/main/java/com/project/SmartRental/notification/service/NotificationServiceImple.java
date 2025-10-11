package com.project.SmartRental.notification.service;

import com.project.SmartRental.notification.dto.req.NotificationReq;
import com.project.SmartRental.notification.dto.res.NotificationRes;
import com.project.SmartRental.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImple implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Page<NotificationRes> getAllNoti(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<NotificationRes> getNotificationById(Long id) {
        return Optional.empty();
    }

    @Override
    public NotificationRes createNotification(NotificationReq notificationReq) {
        return null;
    }

    @Override
    public NotificationRes updateNotification(Long id, NotificationReq notificationReq) {
        return null;
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}

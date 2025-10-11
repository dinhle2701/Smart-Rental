package com.project.SmartRental.notification.service;


import com.project.SmartRental.notification.dto.req.NotificationReq;
import com.project.SmartRental.notification.dto.res.NotificationRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NotificationService {

    Page<NotificationRes> getAllNoti(Pageable pageable);

    Optional<NotificationRes> getNotificationById(Long id);

    NotificationRes createNotification(NotificationReq notificationReq);

    NotificationRes updateNotification(Long id, NotificationReq notificationReq);

    void deleteNotification(Long id);
}

package com.project.SmartRental.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SmartRental.notification.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}

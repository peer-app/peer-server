package com.example.peer.domain.notification.notification.repository;

import com.example.peer.domain.notification.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

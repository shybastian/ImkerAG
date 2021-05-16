package com.example.imkercloudserver.repository;

import com.example.imkercloudserver.repository.entity.Notification;
import com.example.imkercloudserver.repository.entity.NotificationReadStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.user.id = :id AND n.notificationReadStatusType = :notificationReadStatusType")
    List<Notification> findNotificationsByNotificationReadStatusType(
            @Param("id") final Long id,
            @Param("notificationReadStatusType") final NotificationReadStatusType notificationReadStatusType);
}

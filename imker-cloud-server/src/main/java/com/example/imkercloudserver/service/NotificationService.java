package com.example.imkercloudserver.service;

import com.example.imkercloudserver.repository.entity.Notification;
import com.example.imkercloudserver.repository.entity.NotificationReadStatusType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<Notification> findAll();

    List<Notification> findByUserIdAndNotificationReadStatusType(final Long userId, final NotificationReadStatusType type);

    void modifyNotificationReadStatus(final Long id, final Boolean read);

    Notification save(final Notification user);

    void deleteById(final Long id);
}

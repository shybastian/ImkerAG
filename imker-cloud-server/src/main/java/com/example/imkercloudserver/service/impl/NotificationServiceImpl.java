package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.repository.NotificationRepository;
import com.example.imkercloudserver.repository.entity.Notification;
import com.example.imkercloudserver.repository.entity.NotificationReadStatusType;
import com.example.imkercloudserver.service.NotificationService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> findAll() {
        return this.notificationRepository.findAll();
    }

    @Override
    public List<Notification> findByUserIdAndNotificationReadStatusType(Long userId, final NotificationReadStatusType type) {
        return this.notificationRepository.findNotificationsByNotificationReadStatusType(userId, type);
    }

    @Override
    public void modifyNotificationReadStatus(final Long id, Boolean read) {
        Notification entity = notificationRepository.getOne(id);
        if (read){
            entity.setNotificationReadStatusType(NotificationReadStatusType.READ);
        } else {
            entity.setNotificationReadStatusType(NotificationReadStatusType.UNREAD);
        }
        this.notificationRepository.save(entity);
    }

    @Override
    public Notification save(Notification user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

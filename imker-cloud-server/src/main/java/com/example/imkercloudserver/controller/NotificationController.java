package com.example.imkercloudserver.controller;


import com.example.imkercloudserver.controller.dto.NotificationDTO;
import com.example.imkercloudserver.mapper.NotificationMapper;
import com.example.imkercloudserver.repository.entity.Notification;
import com.example.imkercloudserver.repository.entity.NotificationReadStatusType;
import com.example.imkercloudserver.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationMapper notificationMapper;
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<?> getNotificationsForUser(
            @RequestParam(value = "userId") final Long userId,
            @RequestParam(value = "unread", required = false, defaultValue = "true") final boolean unread) {
        final NotificationReadStatusType notificationReadStatusType = unread ? NotificationReadStatusType.UNREAD : NotificationReadStatusType.READ;
        final List<Notification> notifications = this.notificationService.findByUserIdAndNotificationReadStatusType(userId, notificationReadStatusType);
        if (notifications != null && !notifications.isEmpty()) {
            final List<NotificationDTO> dto = new ArrayList<>();
            notifications.forEach(notification -> dto.add(this.notificationMapper.toDto(notification)));
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> modifyNotificationReadStatusType(
            @PathVariable("id") final Long id,
            @RequestParam(value = "read", defaultValue = "true") final Boolean read) {
        this.notificationService.modifyNotificationReadStatus(id, read);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

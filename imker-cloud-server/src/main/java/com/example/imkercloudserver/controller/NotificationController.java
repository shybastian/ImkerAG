package com.example.imkercloudserver.controller;


import com.example.imkercloudserver.controller.dto.NotificationDTO;
import com.example.imkercloudserver.mapper.NotificationMapper;
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
    public ResponseEntity<List<NotificationDTO>> getNotificationsForUser(
            @RequestParam(value = "userId") final Long userId) {
        System.out.println("Called notification. Getting notifications for user with id: " + userId);
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        this.notificationService.findByUserIdAndNotificationReadStatusType(userId, NotificationReadStatusType.UNREAD)
                .forEach(notification -> notificationDTOS.add(this.notificationMapper.toDto(notification)));
        return ResponseEntity.ok(notificationDTOS);
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> modifyNotificationReadStatusType(
        @PathVariable("id") final Long id,
        @RequestParam(value = "read", defaultValue = "true") Boolean read) {
        this.notificationService.modifyNotificationReadStatus(id, read);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

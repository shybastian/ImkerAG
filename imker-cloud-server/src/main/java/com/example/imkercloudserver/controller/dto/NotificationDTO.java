package com.example.imkercloudserver.controller.dto;

import com.example.imkercloudserver.repository.entity.NotificationType;

import java.io.Serializable;

public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 4052633536607576634L;

    private Long id;
    private NotificationType notificationType;
    private String message;
}

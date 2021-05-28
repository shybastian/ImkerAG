package com.example.imkercloudserver.controller.dto;

import com.example.imkercloudserver.repository.entity.NotificationType;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Date;

public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 4052633536607576634L;

    private Long id;
    private NotificationType notificationType;
    private String message;
    private DateTime dateTime;
}

package com.example.imkercloudserver.controller.dto;

import com.example.imkercloudserver.repository.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 4052633536607576634L;

    private Long id;
    private NotificationType notificationType;
    private String message;
    private Date dateTime;
}

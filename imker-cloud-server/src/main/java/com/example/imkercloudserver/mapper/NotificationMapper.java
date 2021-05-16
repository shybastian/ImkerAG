package com.example.imkercloudserver.mapper;

import com.example.imkercloudserver.controller.dto.NotificationDTO;
import com.example.imkercloudserver.repository.entity.Notification;
import org.mapstruct.Mapper;

@Mapper
public interface NotificationMapper {
    NotificationDTO toDto(Notification entity);
}

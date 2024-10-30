package com.fawry.notificationservice.notification.mappers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RequestNotificationMapper {
    Notification toNotification(RequestNotificationDTO requestNotificationDTO);
    RequestNotificationDTO toRequestNotificationDTO(Notification notification);
}

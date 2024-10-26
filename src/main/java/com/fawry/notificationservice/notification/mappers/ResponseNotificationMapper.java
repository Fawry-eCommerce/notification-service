package com.fawry.notificationservice.notification.mappers;

import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.entities.Notification;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ResponseNotificationMapper {
    Notification toNotification(ResponseNotificationDTO responseNotificationDTO);
    ResponseNotificationDTO toResponseNotificationDTO(Notification notification);
}

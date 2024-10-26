package com.fawry.notificationservice.notification.mappers;

import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ResponseNotificationMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true), // Ignore id as it's typically auto-generated
            @Mapping(target = "receiverEmail", source = "responseNotificationDTO.receiverEmail"),
            @Mapping(target = "content", source = "responseNotificationDTO.content"),
            @Mapping(target = "sent", source = "responseNotificationDTO.sent"), // Map directly from source
            @Mapping(target = "createdAt", source = "responseNotificationDTO.createdAt") // Map directly from source
    })
    Notification toNotification(ResponseNotificationDTO responseNotificationDTO);

    @Mappings({
            @Mapping(target = "id", source = "notification.id"),
            @Mapping(target = "receiverEmail", source = "notification.receiverEmail"),
            @Mapping(target = "content", source = "notification.content"),
            @Mapping(target = "sent", source = "notification.sent"), // Map directly from source
            @Mapping(target = "createdAt", source = "notification.createdAt") // Map directly from source
    })
    ResponseNotificationDTO toResponseNotificationDTO(Notification notification);
}

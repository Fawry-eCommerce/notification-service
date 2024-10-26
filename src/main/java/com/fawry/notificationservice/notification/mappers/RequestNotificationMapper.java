package com.fawry.notificationservice.notification.mappers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RequestNotificationMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "receiverEmail", source = "requestNotificationDTO.receiverEmail"),
            @Mapping(target = "content", source = "requestNotificationDTO.content"),
            @Mapping(target = "sent", source = "requestNotificationDTO.sent"),
            @Mapping(target = "createdAt", source = "requestNotificationDTO.createdAt")
    })
    Notification toNotification(RequestNotificationDTO requestNotificationDTO);

    @Mappings({
            @Mapping(target = "receiverEmail", source = "notification.receiverEmail"),
            @Mapping(target = "content", source = "notification.content")
    })
    RequestNotificationDTO toRequestNotificationDTO(Notification notification);
}

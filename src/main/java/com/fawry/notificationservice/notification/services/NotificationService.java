package com.fawry.notificationservice.notification.services;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;

import com.fawry.notificationservice.notification.entities.Notification;
import java.util.List;

public interface NotificationService {
    ResponseNotificationDTO createNotification(RequestNotificationDTO requestNotificationDTO);
    ResponseNotificationDTO findNotificationById(Long id);
    List<ResponseNotificationDTO> listFailedNotifications();
    Notification findById(Long id);
    ResponseNotificationDTO updateNotificationById(Long id, RequestNotificationDTO requestNotificationDTO);
    ResponseNotificationDTO send(RequestNotificationDTO requestNotificationDTO);

    void sendToEmail(ResponseNotificationDTO responseNotificationDTO);
}

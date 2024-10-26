package com.fawry.notificationservice.notification.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ResponseNotificationDTO {
    Long id;
    String receiverEmail;
    String content;
    Boolean sent;
    LocalDateTime createdAt;
}

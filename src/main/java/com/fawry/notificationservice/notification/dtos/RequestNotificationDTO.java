package com.fawry.notificationservice.notification.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RequestNotificationDTO {
    private String receiverEmail;
    private String content;
    private Boolean sent;
}

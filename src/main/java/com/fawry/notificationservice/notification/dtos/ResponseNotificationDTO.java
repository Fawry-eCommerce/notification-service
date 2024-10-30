package com.fawry.notificationservice.notification.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class ResponseNotificationDTO {
    private Long id;
    private String receiverEmail;
    private String subject;
    private String content;
    private Boolean sent;
    private LocalDateTime createdAt;
}

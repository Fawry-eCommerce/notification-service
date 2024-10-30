package com.fawry.notificationservice.notification.dtos;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RequestNotificationDTO {
    @Email(message = "Email Format is Required")
    @NotNull(message = "Receiver Email is Required")
    private String receiverEmail;
    @JsonSetter(nulls = Nulls.SKIP)
    private String subject = "";
    @JsonSetter(nulls = Nulls.SKIP)
    private String content = "";
    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean sent = false;
}

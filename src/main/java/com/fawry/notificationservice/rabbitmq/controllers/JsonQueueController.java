package com.fawry.notificationservice.rabbitmq.controllers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.rabbitmq.producers.RabbitMQJsonProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("notifications")
public class JsonQueueController {
    private final RabbitMQJsonProducer rabbitMQJsonProducer;
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotification(@Valid @RequestBody RequestNotificationDTO requestNotificationDTO){
        rabbitMQJsonProducer.sendJson(requestNotificationDTO);
    }
}

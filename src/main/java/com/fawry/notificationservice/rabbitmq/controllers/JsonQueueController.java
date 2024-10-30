package com.fawry.notificationservice.rabbitmq.controllers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.rabbitmq.consumers.RabbitMQJsonConsumer;
import com.fawry.notificationservice.rabbitmq.producers.RabbitMQJsonProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("notifications")
public class JsonQueueController {
    private final RabbitMQJsonProducer rabbitMQJsonProducer;
//    private final RabbitMQJsonConsumer rabbitMQJsonConsumer;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@Valid @RequestBody RequestNotificationDTO requestNotificationDTO){
        rabbitMQJsonProducer.sendJson(requestNotificationDTO);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body("Notification request has been queued for processing.");
    }

}

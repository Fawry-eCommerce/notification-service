package com.fawry.notificationservice.rabbitmq.consumers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    @Autowired
    private NotificationService notificationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq:queue:json:name}"})
    public ResponseNotificationDTO consume(RequestNotificationDTO requestNotificationDTO){
        LOGGER.info(String.format("Json received: "), requestNotificationDTO);
        return notificationService.send(requestNotificationDTO);
    }
}

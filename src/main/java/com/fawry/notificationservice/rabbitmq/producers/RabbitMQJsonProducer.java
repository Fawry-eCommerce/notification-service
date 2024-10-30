package com.fawry.notificationservice.rabbitmq.producers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq:exchange:json:name}")
    private String exchangeName;
    @Value("${rabbitmq:routing:json:key}")
    private String jsonRoutingKey;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    public void sendJson(RequestNotificationDTO requestNotificationDTO){
        LOGGER.info(String.format("Json sent: "), requestNotificationDTO);
        rabbitTemplate.convertAndSend(exchangeName, jsonRoutingKey, requestNotificationDTO);
    }
}

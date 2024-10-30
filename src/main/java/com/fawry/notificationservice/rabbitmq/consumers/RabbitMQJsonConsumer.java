package com.fawry.notificationservice.rabbitmq.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;

@Service
public class RabbitMQJsonConsumer implements ChannelAwareMessageListener {

    @Autowired
    private NotificationService notificationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = {"${rabbitmq:queue:json:name}"}, ackMode = "MANUAL")
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            RequestNotificationDTO requestNotificationDTO = objectMapper
                    .readValue(message.getBody(), RequestNotificationDTO.class);
            LOGGER.info("Json received: {}", requestNotificationDTO);

            ResponseNotificationDTO response = notificationService.send(requestNotificationDTO);
            LOGGER.info("Json processed successfully");

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LOGGER.error("Error processing notification: ", e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}

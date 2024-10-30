package com.fawry.notificationservice.rabbitmq.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigurations {

    @Value("${rabbitmq:queue:json:name}")
    private String jsonQueueName;
    @Value("${rabbitmq:exchange:json:name}")
    private String exchangeName;
    @Value("${rabbitmq:routing:json:key}")
    private String jsonRoutingKey;
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueueName);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

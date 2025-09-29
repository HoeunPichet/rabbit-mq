package com.example.category_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "category.exchange";
    public static final String QUEUE_NAME = "category.queue.*";
    public static final String ROUTING_KEY = "category.created";

    @Bean
    public TopicExchange categoryExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Consumer 1 queue
    @Bean
    public Queue categoryQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding categoryBinding1(Queue categoryQueue, TopicExchange categoryExchange) {
        return BindingBuilder.bind(categoryQueue).to(categoryExchange).with(ROUTING_KEY);
    }

    // 🔹 JSON Message Converter (used globally)
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 🔹 RabbitTemplate (publisher uses JSON converter too)
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}

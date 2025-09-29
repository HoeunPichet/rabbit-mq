package com.example.category_service.publisher;

import com.example.category_service.config.RabbitMQConfig;
import com.example.category_service.model.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishCategoryCreated(Category category) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    category
            );
            log.info("Published category created event: {}", category.getName());
        } catch (Exception e) {
            log.error("Failed to publish category created event", e);
        }
    }
}
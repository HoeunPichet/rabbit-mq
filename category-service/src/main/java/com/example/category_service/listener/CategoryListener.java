package com.example.category_service.listener;

import com.example.category_service.config.RabbitMQConfig;
import com.example.category_service.model.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryListener {

    @RabbitListener(queues = "category.queue.1")
    public void handleCategoryCreated(Category category) {
        log.info("Received user created event: {} - {}", category.getCategoryId(), category.getName());
    }
}
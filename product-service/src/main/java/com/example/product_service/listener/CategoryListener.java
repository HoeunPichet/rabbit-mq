package com.example.product_service.listener;

import com.example.product_service.model.dto.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryListener {

    @RabbitListener(queues = "category.queue.2")
    public void handleAuditCategoryCreated(Category category) {
        log.info("Received user created event: {} - {}", category.getCategoryId(), category.getName());
    }
}
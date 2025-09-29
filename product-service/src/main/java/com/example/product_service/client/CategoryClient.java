package com.example.product_service.client;

import com.example.product_service.model.dto.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "category-service",
        url = "http://localhost:8082",
        path = "/api/v1/categories")
public interface CategoryClient {
    @GetMapping("/{id}")
    Category findById(@PathVariable("id") Long id);
}

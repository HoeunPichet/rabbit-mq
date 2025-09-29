package com.example.category_service.controller;

import com.example.category_service.model.entity.Category;
import com.example.category_service.model.request.CategoryRequest;
import com.example.category_service.publisher.CategoryPublisher;
import com.example.category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryPublisher categoryPublisher;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = categoryService.createCategory(categoryRequest);
        categoryPublisher.publishCategoryCreated(category);

        return category;
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "Delete successfully!";
    }
}

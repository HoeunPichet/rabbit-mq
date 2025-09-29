package com.example.category_service.service;

import com.example.category_service.model.entity.Category;
import com.example.category_service.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(CategoryRequest productRequest);

    Category updateCategory(Long id, CategoryRequest productRequest);

    void deleteCategory(Long id);
}

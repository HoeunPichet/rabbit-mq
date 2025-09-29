package com.example.category_service.service.impl;

import com.example.category_service.model.entity.Category;
import com.example.category_service.model.request.CategoryRequest;
import com.example.category_service.repository.CategoryRepository;
import com.example.category_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = this.getCategoryById(id);
        category.setName(categoryRequest.getName());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.getCategoryById(id);
        categoryRepository.delete(category);
    }
}

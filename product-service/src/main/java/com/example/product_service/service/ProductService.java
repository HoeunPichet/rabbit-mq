package com.example.product_service.service;

import com.example.product_service.model.dto.ProductDto;
import com.example.product_service.model.entity.Product;
import com.example.product_service.model.request.ProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto createProduct(ProductRequest productRequest);

    ProductDto updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);
}

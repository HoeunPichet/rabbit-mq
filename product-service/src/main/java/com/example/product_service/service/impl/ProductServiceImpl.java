package com.example.product_service.service.impl;

import com.example.product_service.client.CategoryClient;
import com.example.product_service.model.dto.Category;
import com.example.product_service.model.dto.ProductDto;
import com.example.product_service.model.entity.Product;
import com.example.product_service.model.request.ProductRequest;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;


    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(Product::toResponse).toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).orElse(null).toResponse();
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        Category category = categoryClient.findById(productRequest.getCategoryId());

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .categoryId(productRequest.getCategoryId())
                .category(category)
                .build();

        return productRepository.save(product).toResponse();
    }

    @Override
    public ProductDto updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        Category category = categoryClient.findById(productRequest.getCategoryId());

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setCategoryId(product.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product).toResponse();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.delete(product);
    }
}

package com.example.product_service.model.entity;

import com.example.product_service.model.dto.Category;
import com.example.product_service.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false, nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "category_id")
    private Long categoryId;

    @Transient
    private Category category;

    public ProductDto toResponse() {
        return ProductDto.builder()
                .productId(this.productId)
                .name(this.name)
                .price(this.price)
                .category(this.category)
                .build();
    }
}

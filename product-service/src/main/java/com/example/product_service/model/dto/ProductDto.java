package com.example.product_service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Category category;
}

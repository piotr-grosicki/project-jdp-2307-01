package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private long groupProductId;
}

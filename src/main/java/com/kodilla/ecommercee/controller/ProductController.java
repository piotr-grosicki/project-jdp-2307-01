package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.GroupProductDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public List<ProductDto> getProducts() {
        return Arrays.asList(
                new ProductDto(1L, "Product1", "Description1", BigDecimal.valueOf(1.1), 1L),
                new ProductDto(2L, "Product2", "Description2", BigDecimal.valueOf(2.2), 2L)
        );
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return new ProductDto(1L, "Product1", "Description1", BigDecimal.valueOf(1.1), 1L);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        return new ProductDto(1L, "Updated Product", "Updated Description", BigDecimal.valueOf(99.99), 1L);

    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {
    }

}


package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice()
        );
    }

    public ProductDto mapToProductDto(Product product) {
        Long groupProductId = Optional.ofNullable(product.getGroupProduct())
                .map(GroupProduct::getId)
                .orElse(null);

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                groupProductId != null ? groupProductId : 0L
        );
    }
    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

}


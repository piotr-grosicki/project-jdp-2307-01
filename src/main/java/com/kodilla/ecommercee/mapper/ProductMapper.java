package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.GroupProductNotFoundException;
import com.kodilla.ecommercee.service.GroupProductDbService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    private GroupProductDbService groupProductDbService;

    public Product mapToProduct(final ProductDto productDto) throws GroupProductNotFoundException {
        return new Product(productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupProductDbService.getGroupProductById(productDto.getGroupProductId())
        );
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroupProduct().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream().map(this::mapToProductDto).collect(Collectors.toList());
    }
}

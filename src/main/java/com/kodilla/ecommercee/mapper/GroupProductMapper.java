package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.GroupProductDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.GroupProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupProductMapper {
    @Autowired
    private ProductMapper productMapper;

    public GroupProduct mapToGroupProduct(final GroupProductDto groupProductDto) {
        return new GroupProduct(groupProductDto.getId(),
                groupProductDto.getName(),
                getProductList(groupProductDto.getProducts())
        );
    }

    public GroupProductDto mapToGroupProductDto(final GroupProduct groupProduct) {
        return new GroupProductDto(
                groupProduct.getId(),
                groupProduct.getName(),
                productMapper.mapToProductDtoList(groupProduct.getProducts())
        );
    }
    private List<Product> getProductList(final List<ProductDto> productsDto) {
        if (productsDto == null) {
            return new ArrayList<>();
        }

        return productsDto.stream()
                .map(productDto -> {
                    try {
                        return productMapper.mapToProduct(productDto);
                    } catch (GroupProductNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }
}

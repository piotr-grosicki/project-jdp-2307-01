package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.GroupProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupProductMapper {

    public GroupProduct mapToGroupProduct(final GroupProductDto groupProductDto) {
        return new GroupProduct(groupProductDto.getId(),
                groupProductDto.getName(),
                groupProductDto.getProducts() != null ? groupProductDto.getProducts() : new ArrayList<>()
        );
    }

    public GroupProductDto mapToGroupProductDto(final GroupProduct groupProduct) {
        return new GroupProductDto(
                groupProduct.getId(),
                groupProduct.getName(),
                groupProduct.getProducts()
        );
    }
}

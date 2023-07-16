package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.GroupProductDto;
import org.springframework.stereotype.Service;

@Service
public class GroupProductMapper {

    public GroupProduct mapToGroupProduct(final GroupProductDto groupProductDto) {
        return new GroupProduct(
                groupProductDto.getName()
        );
    }

    public GroupProductDto mapToGroupProductDto(final GroupProduct groupProduct) {
        return new GroupProductDto(
                groupProduct.getId(),
                groupProduct.getName()
        );
    }
}

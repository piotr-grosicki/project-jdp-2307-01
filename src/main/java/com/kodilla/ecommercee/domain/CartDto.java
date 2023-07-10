package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class CartDto {

    private boolean isActive;
    private long userId;

    public CartDto(long userId) {
        this.userId = userId;
        this.isActive = true;
    }
}


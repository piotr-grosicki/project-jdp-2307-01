package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class CartDto {
    private long id;
    private String name;
    private boolean isActive;
    private long userId;
    private long orderId;

}


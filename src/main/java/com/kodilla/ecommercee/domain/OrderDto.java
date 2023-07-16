package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long cartId;
    private Long userId;
    private LocalDate created;
    private BigDecimal cost;
}

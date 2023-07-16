package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String address;
    private String userKey;
    private LocalDateTime keyValidity;
    private UserStatus isActive;
    private List<Cart> carts;
    private List<Order> orders;
}

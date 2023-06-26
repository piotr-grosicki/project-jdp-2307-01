package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Long userKey;
    private LocalDate birthday;
    private boolean isBlocked;
}

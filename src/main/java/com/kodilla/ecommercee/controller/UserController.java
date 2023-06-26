package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {

    }

    @PutMapping
    public UserDto blockUser(@RequestBody UserDto userDto) {
        return new UserDto(2L, "John", "Brown", "adress 1", "example@email.com", 1L, LocalDate.of(1994, 5, 9), true);
    }

    @GetMapping(value = "{userId}")
    public Long KeyGeneration(@PathVariable Long userId) throws UserNotFoundException{
        UserDto userDto = new UserDto(2L, "John", "Brown", "adress 1", "example@email.com", 1L, LocalDate.of(1994, 5, 9), true);
        Long TemporaryKey = userDto.getUserKey();
        return TemporaryKey;
    }
}

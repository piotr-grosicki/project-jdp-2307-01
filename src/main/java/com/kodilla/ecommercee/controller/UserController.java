package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.enums.UserStatus;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserDbService userDbService;
    private final UserMapper userMapper;

    List<Cart> carts = new ArrayList<>();
    List<Order> orders = new ArrayList<>();


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public UserDto blockUser(@RequestBody UserDto userDto) {
        return new UserDto (2L, "John", "Brown", "adress 1", 1L, UserStatus.F,carts,orders);
    }

    @GetMapping(value = "{userId}")
    public Long KeyGeneration(@PathVariable Long userId) throws UserNotFoundException{
        UserDto userDto = new UserDto(2L, "John", "Brown", "adress 1", 1L, UserStatus.F,carts,orders);
        Long TemporaryKey = userDto.getUserKey();
        return TemporaryKey;
    }
}

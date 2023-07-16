package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/blockUser/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userDbService.blockUser(userId));
    }

    @PutMapping("/activateUser/{userId}")
    public ResponseEntity<UserDto> activateUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userDbService.activateUser(userId));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<String> KeyGeneration(@PathVariable Long userId) throws UserNotFoundException{
        return ResponseEntity.ok(userDbService.generateKey(userId).getUserKey());
    }
}

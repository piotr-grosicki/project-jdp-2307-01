package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAddress(),
                userDto.getUserKey(),
                userDto.getKeyValidity(),
                userDto.getIsActive(),
                userDto.getCarts(),
                userDto.getOrders()
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getAddress(),
                user.getUserKey(),
                user.getKeyValidity(),
                user.getIsActive(),
                user.getCarts(),
                user.getOrders()
        );
    }
}

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final UserRepository userRepository;
    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        if(cartDto.getId() > 0) {
            return new Cart(cartDto.getId(), cartDto.isActive(), userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new));
        } else {
            return new Cart(true, userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new));
        }
    }
}

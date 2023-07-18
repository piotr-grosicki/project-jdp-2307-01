package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        return new Order(
                orderDto.getId() != null ? orderDto.getId() : 0l,
                orderDto.getCost(),
                orderDto.getCreated(),
                userRepository.findById(orderDto.getUserId()).orElseThrow(UserNotFoundException::new),
                cartRepository.findById(orderDto.getCartId()).orElseThrow(CartNotFoundException::new));
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCart().getId(),
                order.getUser().getId(),
                order.getCreated(),
                order.getCost()
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}

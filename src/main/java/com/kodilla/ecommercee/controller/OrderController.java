package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderDbService orderDbService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orderDbService.getAllOrders()));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        orderDbService.saveOrder(orderMapper.mapToOrder(orderDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderDbService.getOrder(orderId)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException, UserNotFoundException, CartNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderDbService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}

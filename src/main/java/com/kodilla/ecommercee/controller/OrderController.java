package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderDbService orderDbService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        List<OrderDto>orderDtos = orderMapper.mapToOrderDtoList(orders);
        return ResponseEntity.ok(orderDtos);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, CartNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        try {
            Order order = orderDbService.getOrder(orderId);
            OrderDto orderDto = orderMapper.mapToOrderDto(order);
            return ResponseEntity.ok(orderDto);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with given id doesn't exist: (");
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        try {
            Order existingOrder = orderDbService.getOrder(orderId);
            Order updatedOrder = orderMapper.mapToOrder(orderDto);
            existingOrder.setCart(updatedOrder.getCart());
            existingOrder.setCost(updatedOrder.getCost());
            existingOrder.setUser(updatedOrder.getUser());
            existingOrder.setCreated(updatedOrder.getCreated());
            orderDbService.saveOrder(existingOrder);
            return ResponseEntity.ok().build();
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with given id doesn't exist: (");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with given id doesn't exist: (");
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with given id doesn't exist: (");
        }
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            orderDbService.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with given id doesn't exist: (");
        }
    }
}

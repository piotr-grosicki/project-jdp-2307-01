package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders(){
        return Arrays.asList(new OrderDto(1L, "Order1", 1L, 1L, LocalDate.of(2023,6,24),new BigDecimal(100)),
                new OrderDto(2L, "Order2", 2L, 2L, LocalDate.of(2022,1,1),new BigDecimal(500)));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(@RequestBody OrderDto orderDto){

    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) throws OrderNotFoundException{
        return new OrderDto(2L, "Order2", 2L, 2L, LocalDate.of(2022,1,1),new BigDecimal(500));
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(3L, "Order3", 3L, 3L, LocalDate.of(2023,3,3),new BigDecimal(500));
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException{

    }
}

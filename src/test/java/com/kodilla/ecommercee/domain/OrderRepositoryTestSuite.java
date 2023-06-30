package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.enums.UserStatus;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
public class OrderRepositoryTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    private Product product1;
    private Product product2;
    private User user;
    private Cart cart;


}

package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.enums.UserStatus;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional

public class OrderRepositoryTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private GroupProductRepository groupProductRepository;

    private Cart cart;
    private Product product1;
    private Product product2;
    private User user;
    private GroupProduct food;

    @BeforeEach
    void preparingData() {
        user = new User("John", "Smith", "Poland",1L, UserStatus.T );
        food = new GroupProduct("Food");
        product1 = new Product("Apple", "Fruit", new BigDecimal(1.23));
        product2 = new Product("Potato", "Vegetables", new BigDecimal(1.45));
        product1.setGroupProduct(food);
        product2.setGroupProduct(food);
        cart = new Cart(true, user);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
    }
    @Test
    void savingOrderTest() {
        //Given
        Order order = new Order(new BigDecimal(23), user, cart);

        //When
        orderRepository.save(order);
        //Then
        long orderId = order.getId();
        long userId = user.getId();
        long product1Id = product1.getId();
        long product2Id = product2.getId();
        long cartId = cart.getId();
        long groupId = food.getId();
        Optional<Order> readOrder = orderRepository.findById(orderId);
        Optional<User> readUser = userRepository.findById(userId);
        Optional<Product> readProduct = productRepository.findById(product1Id);
        Optional<Cart> readCart = cartRepository.findById(cartId);
        Optional<GroupProduct> readGroup = groupProductRepository.findById(groupId);


        Assertions.assertTrue(readOrder.isPresent());
        Assertions.assertTrue(readUser.isPresent());
        Assertions.assertTrue(readProduct.isPresent());
        Assertions.assertTrue(readCart.isPresent());
        Assertions.assertTrue(readGroup.isPresent());


        //CleanUp
        orderRepository.deleteById(orderId);
        userRepository.deleteById(userId);
        productRepository.deleteById(product1Id);
        productRepository.deleteById(product2Id);
        groupProductRepository.deleteById(groupId);


    }
    @Test
    void deletingOrderWithUserTest() {
        //Given
        Order order = new Order(new BigDecimal(23), user, cart);
        orderRepository.save(order);
        long orderId = order.getId();
        long userId = user.getId();
        //When
        orderRepository.deleteById(orderId);
        Optional<User> readUser = userRepository.findById(userId);
        //Then
        Assertions.assertTrue(readUser.isPresent());
        //CleanUp
        userRepository.deleteById(user.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        groupProductRepository.deleteById(food.getId());
    }

    @Test
    void deletingOrderWithProductTest() {
        //Given
        Order order = new Order(new BigDecimal(23), user, cart);
        orderRepository.save(order);
        long orderId = order.getId();
        long product1Id = product1.getId();
        long product2Id = product2.getId();
        //When
        orderRepository.deleteById(orderId);
        Optional<Product> readProduct = productRepository.findById(product1Id);
        Optional<Product> readProduct1 = productRepository.findById(product2Id);
        //Then
        Assertions.assertTrue(readProduct.isPresent());
        Assertions.assertTrue(readProduct1.isPresent());

        //CleanUp
        userRepository.deleteById(user.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        groupProductRepository.deleteById(food.getId());
    }
    @Test
    void deletingOrderWithGroupTest() {
        //Given
        Order order = new Order(new BigDecimal(23), user, cart);
        orderRepository.save(order);
        long orderId = order.getId();
        long groupId = food.getId();
        //When
        orderRepository.deleteById(orderId);
        Optional<GroupProduct> readGroup = groupProductRepository.findById(groupId);
        //Then
        Assertions.assertTrue(readGroup.isPresent());
        //CleanUp
        userRepository.deleteById(user.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        groupProductRepository.deleteById(food.getId());
    }
    @Test
    void findAllTest() {
        //Given
        Order order1 = new Order(new BigDecimal(30), user, cart);
        Cart cart2 = new Cart(true,user);
        cart2.getProducts().add(product1);
        Order order2 = new Order(new BigDecimal(25), user, cart2);
        orderRepository.save(order1);
        orderRepository.save(order2);
        //When
        List<Order> orders = orderRepository.findAll();
        //Then
        Assertions.assertEquals(2, orders.size());
        //CleanUp
        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
        userRepository.deleteById(user.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        groupProductRepository.deleteById(food.getId());
    }

}

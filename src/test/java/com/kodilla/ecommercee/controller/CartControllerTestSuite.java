package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class CartControllerTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateCartTest() {
        // WHEN
        User user = new User();
        userRepository.save(user);

        Cart cart = new Cart(true, user);
        cartRepository.save(cart);

        // THEN
        assertTrue(cartRepository.existsById(cart.getId()));

        // Clean up
        cartRepository.deleteById(cart.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    public void shouldDeleteCartTest() {
        // GIVEN
        User user = new User();
        userRepository.save(user);

        Cart cart = new Cart(true, user);
        cartRepository.save(cart);

        // WHEN
        cartRepository.deleteById(cart.getId());

        // THEN
        assertFalse(cartRepository.existsById(cart.getId()));

        // Clean up
        userRepository.delete(user);
    }

    @Test
    public void shouldReadCartTestWithProducts() {
        // GIVEN
        User user = new User();
        userRepository.save(user);

        Cart cart = new Cart(true, user);
        cartRepository.save(cart);

        Product product1 = new Product();
        product1.setName("Product 1");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        productRepository.save(product2);

        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);

        // WHEN
        Optional<Cart> retrievedCartOptional = cartRepository.findById(cart.getId());

        // THEN
        assertTrue(retrievedCartOptional.isPresent());
        Cart retrievedCart = retrievedCartOptional.get();
        assertEquals(cart.getId(), retrievedCart.getId());
        assertEquals(user, retrievedCart.getUser());
        List<Product> retrievedProducts = retrievedCart.getProducts();
        assertEquals(2, retrievedProducts.size());
        assertTrue(retrievedProducts.containsAll(Arrays.asList(product1, product2)));

        // Clean up
        cartRepository.deleteById(cart.getId());
        userRepository.delete(user);
        productRepository.delete(product1);
        productRepository.delete(product2);
    }

    @Test
    public void updateCartTest() {
        // GIVEN
        User user = new User();
        userRepository.save(user);

        Cart cart = new Cart(true, user);
        cartRepository.save(cart);

        // WHEN
        cart.setActive(false);
        cartRepository.save(cart);

        // THEN
        Cart updatedCart = cartRepository.findById(cart.getId()).orElse(null);
        assertNotNull(updatedCart);
        assertFalse(updatedCart.isActive());

        // Clean up
        cartRepository.deleteById(cart.getId());
        userRepository.deleteById(user.getId());
    }
}



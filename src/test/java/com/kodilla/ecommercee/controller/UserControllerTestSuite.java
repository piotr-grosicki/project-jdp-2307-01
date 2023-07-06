package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.enums.UserStatus;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class UserControllerTestSuite {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;

    @AfterEach
    void cleanUp(){
        userRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    void createUserTest(){
        //Given
        User user = new User();
        //WHen
        userRepository.save(user);
        //Then
        assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    void updateUserTest(){
        //Given
        User user = new User("Adam",  UserStatus.T);
        userRepository.save(user);
        //When
        user.setIsActive(UserStatus.F);
        userRepository.save(user);
        User updatedUser = userRepository.findById(user.getId()).orElse(null);
        //Then
        assertNotNull(updatedUser);
        assertEquals(UserStatus.F, updatedUser.getIsActive());
    }

    @Test
    void deleteByIdUserTest(){
        //Given
        User user = new User();
        //When
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        //Then
        assertEquals(0,userRepository.findAll().size());
        assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    void findByIdUserTest(){
        //Given
        User user = new User( "Adam",UserStatus.T);
        //When
        userRepository.save(user);
        //Then
        Long id = user.getId();
        assertTrue(userRepository.findById(id).isPresent());
    }

    @Test
    void findAllUsers(){
        //Given
        User user = new User();
        User user2 = new User();
        userRepository.save(user);
        userRepository.save(user2);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertEquals(2,users.size());
    }
}
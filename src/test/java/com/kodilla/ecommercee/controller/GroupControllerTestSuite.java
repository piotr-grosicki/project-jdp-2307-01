package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.repository.GroupProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class GroupControllerTestSuite {

    @Autowired
    private GroupProductRepository groupProductRepository;

    @Test
    public void testSave() {
        //Given
        GroupProduct groupProduct = new GroupProduct(1, "Fruit");

        //When
        groupProductRepository.save(groupProduct);

        //Then
        long id = groupProduct.getId();
        Optional<GroupProduct> readGroup = groupProductRepository.findById(id);
        assertTrue(readGroup.isPresent());

        //CleanUp
        groupProductRepository.deleteById(id);
    }

    @Test
    public void testFindAll() {
        //Given
        GroupProduct groupProduct1 = new GroupProduct(1, "Fruit");
        GroupProduct groupProduct2 = new GroupProduct(2, "Vegetable");
        groupProductRepository.save(groupProduct1);
        groupProductRepository.save(groupProduct2);

        //When
        List<GroupProduct> readGroupProduct = groupProductRepository.findAll();

        //Then
        assertEquals(2, readGroupProduct.size());

        //CleanUp
        long id1 = readGroupProduct.get(0).getId();
        long id2 = readGroupProduct.get(1).getId();
        groupProductRepository.deleteById(id1);
        groupProductRepository.deleteById(id2);
    }

    @Test
    public void testFindById() {
        //Given
        GroupProduct groupProduct = new GroupProduct(1, "Fruit");

        //When
        groupProductRepository.save(groupProduct);

        //Then
        assertTrue(groupProductRepository.findById(1L).isPresent());

        //CleanUp
        long id = groupProduct.getId();
        groupProductRepository.deleteById(id);
    }
}
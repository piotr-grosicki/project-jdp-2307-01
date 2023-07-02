package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.repository.GroupProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class GroupControllerTestSuite {

    @Autowired
    private GroupProductRepository groupProductRepository;

    @Test
    public void testSave() {
        //Given
        GroupProduct groupProduct = new GroupProduct("Fruit");

        //When
        groupProductRepository.save(groupProduct);

        //Then
        long id = groupProduct.getId();
        List<GroupProduct> readGroup = groupProductRepository.findAll();
        assertEquals(1, readGroup.size());

        //CleanUp
        groupProductRepository.deleteById(id);
    }

    @Test
    public void testFindAll() {
        //Given
        GroupProduct groupProduct1 = new GroupProduct("Fruit");
        GroupProduct groupProduct2 = new GroupProduct("Vegetable");
        groupProductRepository.save(groupProduct1);
        groupProductRepository.save(groupProduct2);

        //When
        List<GroupProduct> readGroup = groupProductRepository.findAll();

        //Then
        assertEquals(2, readGroup.size());

        //CleanUp
        long id1 = readGroup.get(0).getId();
        long id2 = readGroup.get(1).getId();
        groupProductRepository.deleteById(id1);
        groupProductRepository.deleteById(id2);
    }

    @Test
    public void testFindById() {
        //Given
        GroupProduct groupProduct = new GroupProduct("Fruit");

        //When
        groupProductRepository.save(groupProduct);

        //Then
        long id = groupProduct.getId();
        assertTrue(groupProductRepository.findById(id).isPresent());

        //CleanUp
        groupProductRepository.deleteById(id);
    }

    @Test
    public void testDeleteById() {
        //Given
        GroupProduct groupProduct = new GroupProduct("Fruit");
        groupProductRepository.save(groupProduct);

        //When
        long id = groupProduct.getId();
        groupProductRepository.deleteById(id);

        //Then
        assertFalse(groupProductRepository.findById(id).isPresent());

    }
}
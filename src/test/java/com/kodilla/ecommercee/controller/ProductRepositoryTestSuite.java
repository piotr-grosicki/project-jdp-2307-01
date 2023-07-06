package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupProductRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    GroupProductRepository groupProductRepository;

    private static final String GROUP = "Fruit";
    private static final String NAME1 = "Apple";
    private static final String NAME2 = "Plum";
    private static final String DESCRIPTION = "Fresh and tasty";

    @Test
    public void testSave() {
        //Given
        Product product = new Product(NAME1, DESCRIPTION, new BigDecimal(1));

        //When
        productRepository.save(product);
        long id1 = product.getId();

        //Then
        List<Product> resultList = productRepository.findAll();
        assertEquals(1, resultList.size());

        //CleanUp
        productRepository.deleteById(id1);
    }
    @Test
    public void testDelete() {
        //Given
        GroupProduct groupProduct = new GroupProduct(GROUP);
        Product product = new Product(NAME1, DESCRIPTION, new BigDecimal(1));
        groupProductRepository.save(groupProduct);
        product.setGroupProduct(groupProduct);
        productRepository.save(product);
        long id = product.getId();
        long id2 = groupProduct.getId();

        //When
        productRepository.deleteById(id);

        //Then
        assertFalse(productRepository.findById(id).isPresent());
        assertTrue(groupProductRepository.findById(id2).isPresent());

        //CleanUp
        groupProductRepository.deleteById(id2);
    }

    @Test
    public void testFindAll() {
        //Given
        Product product1 = new Product(NAME1, DESCRIPTION, new BigDecimal(1));
        Product product2 = new Product(NAME2, DESCRIPTION, new BigDecimal(2));
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        List<Product> resultList = productRepository.findAll();

        //Then
        assertEquals(2, resultList.size());

        //CleanUp
        long id1 = resultList.get(0).getId();
        long id2 = resultList.get(1).getId();
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
    }

    @Test
    public void testFindById() {
        //Given
        Product product = new Product(NAME1, DESCRIPTION, new BigDecimal(1));

        //When
        productRepository.save(product);

        //Then
        long id = product.getId();
        assertTrue(productRepository.findById(id).isPresent());

        //CleanUp
        productRepository.deleteById(id);
    }

    @Test
    public void testFindAllByName() {
        //Given
        Product product1 = new Product(NAME1, DESCRIPTION, new BigDecimal(1));
        Product product2 = new Product(NAME2, DESCRIPTION, new BigDecimal(2));
        productRepository.save(product1);
        productRepository.save(product2);
        String name = product1.getName();
        String name2 = product2.getName();

        //When
        List<Product> resultList = productRepository.findAllByName(name);
        List<Product> resultList2 = productRepository.findAllByName(name2);

        //Then
        assertEquals(1, resultList.size());
        assertEquals(1, resultList2.size());

        //CleanUp
        long id = resultList.get(0).getId();
        long id2 = resultList2.get(0).getId();
        productRepository.deleteById(id);
        productRepository.deleteById(id2);
    }
}
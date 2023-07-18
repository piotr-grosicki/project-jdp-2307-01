package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDbService {
    @Autowired
    private final ProductRepository productRepository;

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(Long id) throws ProductNotFoundException {
        Optional<Product> result = Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)));
        return result;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(final Long id) {
        productRepository.deleteById(id);
    }
}

package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
    }
    @GetMapping(value = "{cartId}")
    public List<ProductDto> getProductsFromCart(@PathVariable long cartId) {
        return Arrays.asList(new ProductDto(1l,"Soap","Chemisty", new BigDecimal(3), 3L), new ProductDto(2l,"Muszyna","Food", new BigDecimal(2), 2L));
    }
    @PutMapping(value = "{cartId}")
    public void addProduct(@PathVariable long cartId, @RequestBody ProductDto productDto) {
    }
    @DeleteMapping(value = "{cartId}")
    public void removeProduct(@PathVariable long cartId, @RequestBody ProductDto productDto) {
    }
    @PostMapping(value = "{cartId}")
    public void createOrder(@PathVariable long cartId) {
    }

}

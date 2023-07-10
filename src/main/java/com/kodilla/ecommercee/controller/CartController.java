package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductNotFoundException;
import com.kodilla.ecommercee.exception.AllreadyActiveCartException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.NotActiveCartException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartController {
    private final CartMapper cartMapper;
    private final CartService cartService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException, AllreadyActiveCartException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.createCart(cart);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "{cartId}")
    public List<ProductDto> getProductsFromCart(@PathVariable long cartId) {
        return Arrays.asList(new ProductDto(1l,"Soap","Chemisty", new BigDecimal(3), 3L), new ProductDto(2l,"Muszyna","Food", new BigDecimal(2), 2L));
    }
    @PutMapping(value = "{cartId}")
    public ResponseEntity<Void> addProduct(@PathVariable long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException, NotActiveCartException {
        cartService.addProduct(cartId,productId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> removeProduct(@PathVariable long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException, NotActiveCartException {
        cartService.removeProduct(cartId,productId);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value = "{cartId}")
    public void createOrder(@PathVariable long cartId) {
    }

}

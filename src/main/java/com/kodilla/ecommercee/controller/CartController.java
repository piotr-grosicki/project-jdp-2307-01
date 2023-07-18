package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/cart")
public class CartController {
    private final CartMapper cartMapper;
    private final CartDbService cartService;
    private final ProductMapper productMapper;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException, AllreadyActiveCartException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.createCart(cart);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getProductsFromCart(@PathVariable long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDtoList(cartService.getProductsFromCart(cartId)));
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
    public ResponseEntity<Void> createOrder(@PathVariable long cartId) throws CartNotFoundException, NotAutorizedUserException {
        cartService.makeOrder(cartId);
        return ResponseEntity.ok().build();
    }

}

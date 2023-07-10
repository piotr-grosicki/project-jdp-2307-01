package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductNotFoundException;
import com.kodilla.ecommercee.exception.AllreadyActiveCartException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.NotActiveCartException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void createCart (Cart cart) throws AllreadyActiveCartException {
        for (Cart userCart : cart.getUser().getCarts()) {
            if(userCart.isActive()) {
                throw new AllreadyActiveCartException();
            }
        }
        cartRepository.save(cart);
        cart.getUser().getCarts().add(cart);
        userRepository.save(cart.getUser());
    }

    public void addProduct(long cartId, long productId) throws CartNotFoundException, ProductNotFoundException, NotActiveCartException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        if(cart.isActive()) {
            cart.getProducts().add(product);
            cartRepository.save(cart);
        }else {
            throw new NotActiveCartException();
        }
    }
    public void removeProduct(long cartId, long productId) throws CartNotFoundException, ProductNotFoundException, NotActiveCartException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        if(cart.isActive()) {
            cart.getProducts().remove(product);
            cartRepository.save(cart);
        }else {
            throw new NotActiveCartException();
        }
    }
}

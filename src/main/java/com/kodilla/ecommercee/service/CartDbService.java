package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.AllreadyActiveCartException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.NotActiveCartException;
import com.kodilla.ecommercee.exception.NotAutorizedUserException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDbService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final OrderRepository orderRepository;

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
    public List<Product> getProductsFromCart (long cartId) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return cart.getProducts();
    }

    public Order makeOrder(long cartId)throws CartNotFoundException, NotAutorizedUserException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        User user = cart.getUser();
        BigDecimal cost = cart.getProducts().stream().map(Product::getPrice)
                .reduce(BigDecimal.ZERO,(sum, price) -> sum = sum.add(price));

        if(userDbService.isKeyValid(userMapper.mapToUserDto(user))) {
            Order order =new Order(cost,user,cart);
            cart.setActive(false);
            cartRepository.save(cart);
            orderRepository.save(order);
            user.getOrders().add(order);
            userRepository.save(user);
            return order;
        }else {
            throw new NotAutorizedUserException();
        }
    }
}

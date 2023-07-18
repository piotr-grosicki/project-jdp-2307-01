package com.kodilla.ecommercee.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(long productId){
        super(String.format("Product with id %s not found", productId));
    }
}

package com.kodilla.ecommercee.controller;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException (long id) {
        super(String.format("Order with id %s not found", id));
    }
}

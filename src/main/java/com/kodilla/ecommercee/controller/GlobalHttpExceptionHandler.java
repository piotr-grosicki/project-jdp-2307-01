package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductNotFoundException;
import com.kodilla.ecommercee.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalHttpExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> cartNotFoundHandler(CartNotFoundException e) {
        return new ResponseEntity<>("Cart with given id does not exist", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException e) {
        return new ResponseEntity<>("User with given id does not exist", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFoundHandler(ProductNotFoundException e) {
        return new ResponseEntity<>("Product with given id does not exist", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AllreadyActiveCartException.class)
    public ResponseEntity<Object> activeCartHandler(AllreadyActiveCartException e) {
        return new ResponseEntity<>("For this user exist active cart",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotActiveCartException.class)
    public ResponseEntity<Object> notActiveCartHandler(NotActiveCartException e) {
        return new ResponseEntity<>("This cart is not active", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotAutorizedUserException.class)
    public ResponseEntity<Object> notAutorizedUserHandler(NotAutorizedUserException e) {
        return new ResponseEntity<>("Akces forbidden", HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> orderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>("Order with given id does not exist", HttpStatus.NOT_FOUND);
    }

}

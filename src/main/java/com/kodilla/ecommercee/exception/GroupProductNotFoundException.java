package com.kodilla.ecommercee.exception;

public class GroupProductNotFoundException extends Exception{

    public GroupProductNotFoundException(long id){
        super("GroupProduct with " + id + " not found.");
    }
}
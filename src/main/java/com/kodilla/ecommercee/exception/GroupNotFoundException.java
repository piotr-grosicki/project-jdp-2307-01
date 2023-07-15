package com.kodilla.ecommercee.exception;

public class GroupNotFoundException extends RuntimeException {
        public GroupNotFoundException(long Id){
    super(String.format("Group with id %s not found", Id));
}
}

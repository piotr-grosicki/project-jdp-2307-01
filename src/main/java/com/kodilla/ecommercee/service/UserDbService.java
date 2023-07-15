package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.enums.UserStatus;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {
    @Autowired
    private final UserRepository repository;

    public List<User> getAllusers(){
        return repository.findAll();
    }

    public User createUser(final User user){
        return repository.save(user);
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(final Long userId){
        repository.deleteById(userId);
    }

    public void blockUser(final Long userId) throws UserNotFoundException{
        User userToBlock = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (userToBlock.getIsActive() == UserStatus.T){
            userToBlock.setIsActive(UserStatus.F);
        } else {
            System.out.println("User is already blocked");
        }
    }

    public void activateUser(final Long userId) throws  UserNotFoundException{
        User userToActivate = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (userToActivate.getIsActive() == UserStatus.F){
            userToActivate.setIsActive(UserStatus.T);
        }else {
            System.out.println("User is still active");
        }
    }
}

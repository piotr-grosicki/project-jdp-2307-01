package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.enums.UserStatus;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDbService {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final UserMapper mapper;
    private final static int VALIDITY_HOURS = 1;

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

    public UserDto blockUser(final Long userId) throws UserNotFoundException{
        User userToBlock = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        userToBlock.setIsActive(UserStatus.F);
        repository.save(userToBlock);
        return mapper.mapToUserDto(createUser(userToBlock));

    }

    public UserDto activateUser(final Long userId) throws  UserNotFoundException{
        User userToActivate = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        userToActivate.setIsActive(UserStatus.T);
        repository.save(userToActivate);
        return mapper.mapToUserDto(createUser(userToActivate));
    }

    public UserDto generateKey(final Long userId) throws UserNotFoundException{
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();
        user.setUserKey(key);
        user.setKeyValidity(LocalDateTime.now().plus(VALIDITY_HOURS, ChronoUnit.HOURS));
        repository.save(user);
        return mapper.mapToUserDto(user);
    }

    public boolean isKeyValid(final UserDto userDto){
        LocalDateTime creationTime = userDto.getKeyValidity();
        return LocalDateTime.now().isBefore(creationTime);
    }
}
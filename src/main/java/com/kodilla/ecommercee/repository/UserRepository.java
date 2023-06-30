package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long id);

    @Override
    List<User> findAll();

    @Override
    void deleteById(Long id);

    List<User> findByFirstname(String firstname);
    List<User> findByLastnameame(String lastname);
}

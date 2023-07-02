package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.GroupProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface GroupProductRepository extends CrudRepository<GroupProduct, Long> {
    @Override
    List<GroupProduct> findAll();

    @Override
    GroupProduct save(GroupProduct groupProduct);

    @Override
    Optional<GroupProduct> findById(Long id);

    @Override
    void deleteById(Long id);
}

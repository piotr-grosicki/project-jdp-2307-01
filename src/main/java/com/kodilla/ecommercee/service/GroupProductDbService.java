package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.exception.GroupProductNotFoundException;
import com.kodilla.ecommercee.repository.GroupProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupProductDbService {

    @Autowired
    private final GroupProductRepository repository;

    public List<GroupProduct> getAllGroups(){
        return repository.findAll();
    }

    public GroupProduct saveGroup(GroupProduct groupProduct){
        return repository.save(groupProduct);
    }

    public GroupProduct getGroupProductById(Long id) throws GroupProductNotFoundException{
        return repository.findById(id).orElseThrow(() -> new GroupProductNotFoundException(id));
    }

    public void deleteGroupProduct(long id){
        repository.deleteById(id);
    }



}
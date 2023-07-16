package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProduct;
import com.kodilla.ecommercee.domain.GroupProductDto;
import com.kodilla.ecommercee.exception.GroupProductNotFoundException;
import com.kodilla.ecommercee.mapper.GroupProductMapper;
import com.kodilla.ecommercee.service.GroupProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GroupController {

    private final GroupProductDbService groupProductDbService;
    private final GroupProductMapper groupProductMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addGroup(@RequestBody GroupProductDto groupProductDto) {
        GroupProduct groupProduct = groupProductMapper.mapToGroupProduct(groupProductDto);
        groupProductDbService.saveGroup(groupProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<GroupProductDto>> getGroups() {

        return ResponseEntity.ok(groupProductDbService.getAllGroups().stream()
                .map(groupProductMapper::mapToGroupProductDto)
                .toList());
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupProductDto> getGroup(@PathVariable long groupId) throws GroupProductNotFoundException {
        return ResponseEntity.ok(groupProductMapper.mapToGroupProductDto(groupProductDbService.getGroupProductById(groupId)));
    }

    @PutMapping
    public ResponseEntity<GroupProductDto> updateGroup(@RequestBody GroupProductDto groupProductDto) {
        GroupProduct groupProduct = groupProductMapper.mapToGroupProduct(groupProductDto);
        GroupProduct savedGroupProduct = groupProductDbService.saveGroup(groupProduct);

        return ResponseEntity.ok(groupProductMapper.mapToGroupProductDto(savedGroupProduct));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroupProduct(@PathVariable long groupId) throws GroupProductNotFoundException {

        try {
            groupProductDbService.deleteGroupProduct(groupId);
        } catch (EmptyResultDataAccessException e) {
            throw new GroupProductNotFoundException(groupId);
        }

        return ResponseEntity.ok().build();
    }

}

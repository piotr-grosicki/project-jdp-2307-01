package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupProductDto groupProductDto) {
    }
    @GetMapping
    public List<GroupProductDto> getGroups() {
        return Arrays.asList(new GroupProductDto(1L, "Food"), new GroupProductDto(2, "Toys"));
    }
    @GetMapping(value = "{groupId}")
    public GroupProductDto getGroup(@PathVariable long groupId) {
        return new GroupProductDto(1L, "Food");
    }
    @PutMapping
    public GroupProductDto updateGroup(@RequestBody GroupProductDto groupProductDto) {
        return new GroupProductDto(1L,"Not food");
    }

}

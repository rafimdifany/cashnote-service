package com.practice.cashnoteservice.controller;

import com.practice.cashnoteservice.dto.UserDto;
import com.practice.cashnoteservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Page<UserDto> getAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PostMapping
    public UserDto insert(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }
}

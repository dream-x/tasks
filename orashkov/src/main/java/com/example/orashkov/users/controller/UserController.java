package com.example.orashkov.users.controller;

import com.example.orashkov.common.locationbuilder.LocationBuilder;
import com.example.orashkov.common.mappers.OptionalMapper;
import com.example.orashkov.users.dto.UserDto;
import com.example.orashkov.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public UserDto findUser(@PathVariable("id") Long id) {
        return OptionalMapper.getDto(userService.findById(id));
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.saveUser(userDto);
        return LocationBuilder.getResponseEntity(savedUserDto.getId());
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(userDto);
        return LocationBuilder.getResponseEntity(updatedUserDto.getId());
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}

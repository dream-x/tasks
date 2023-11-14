package com.example.orashkov.users.service;

import com.example.orashkov.users.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findById(Long id);

    List<UserDto> getAllUsers();

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteById(Long id);
}

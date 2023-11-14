package com.example.orashkov.users.repository;

import com.example.orashkov.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.example.demoerp.home.user.repository;

import com.example.demoerp.home.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserByUsername(String name);
}

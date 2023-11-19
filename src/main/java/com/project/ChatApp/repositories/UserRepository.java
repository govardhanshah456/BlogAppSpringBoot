package com.project.ChatApp.repositories;

import com.project.ChatApp.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);
}

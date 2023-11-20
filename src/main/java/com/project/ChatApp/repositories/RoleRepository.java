package com.project.ChatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ChatApp.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}

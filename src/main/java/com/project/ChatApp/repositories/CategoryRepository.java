package com.project.ChatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ChatApp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}

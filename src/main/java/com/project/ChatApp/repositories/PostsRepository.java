package com.project.ChatApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ChatApp.entities.Category;
import com.project.ChatApp.entities.Posts;
import com.project.ChatApp.entities.User;

public interface PostsRepository extends JpaRepository<Posts,Long>{
	List<Posts> findByUser(User user);
	List<Posts> findByCategory(Category category);
}

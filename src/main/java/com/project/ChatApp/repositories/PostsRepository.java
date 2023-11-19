package com.project.ChatApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ChatApp.entities.Category;
import com.project.ChatApp.entities.Posts;
import com.project.ChatApp.entities.User;
import com.project.ChatApp.payloads.PostsDto;

public interface PostsRepository extends JpaRepository<Posts,Long>{
	List<Posts> findByUser(User user);
	List<Posts> findByCategory(Category category);
	@Query("SELECT p from Posts p where p.title like :keyword or p.content like :keyword")
	List<Posts> searchPost(String keyword);
}

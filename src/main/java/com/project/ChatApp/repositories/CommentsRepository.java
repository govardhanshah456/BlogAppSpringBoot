package com.project.ChatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ChatApp.entities.Comments;

public interface CommentsRepository extends JpaRepository<Comments,Long>{

}

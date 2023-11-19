package com.project.ChatApp.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.project.ChatApp.payloads.PostResponse;
import com.project.ChatApp.payloads.PostsDto;

public interface PostsService {
	PostsDto createPost(PostsDto post,Long userId,long categoryId);
	PostsDto updatePost(PostsDto post,Long postId);
	void deletePost(Long postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy, String sortDir);
	PostsDto getPostById(Long postId);
	List<PostsDto> getPostsByUser(Long userId);
	List<PostsDto> getPostsByCategory(Long categoryId);
	List<PostsDto> searchPost(String keyword);
}

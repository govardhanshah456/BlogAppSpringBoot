package com.project.ChatApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ChatApp.payloads.PostsDto;
import com.project.ChatApp.services.PostsService;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/{userId}/{categoryId}/create")
	private ResponseEntity<PostsDto>createPost(PostsDto post,@PathVariable Long userId,@PathVariable Long categoryId){
		PostsDto postDto=this.postsService.createPost(post, userId, categoryId);
		return new ResponseEntity<PostsDto>(postDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{postId}/update")
	private ResponseEntity<PostsDto>updatePost(PostsDto post,@PathVariable Long postId){
		PostsDto postDto=this.postsService.updatePost(post, postId);
		return new ResponseEntity<PostsDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}/delete")
	private ResponseEntity<String>deletePost(@PathVariable Long postId){
		this.postsService.deletePost(postId);
		return new ResponseEntity<String>("Deleted Post Successfully!",HttpStatus.OK);
	}
	
	@GetMapping("")
	private ResponseEntity<List<PostsDto>>getAllPosts(){
		List<PostsDto> postDto=this.postsService.getAllPosts();
		return new ResponseEntity<List<PostsDto>>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	private ResponseEntity<PostsDto>getPostById(@PathVariable Long postId){
		PostsDto post=this.postsService.getPostById(postId);
		return new ResponseEntity<PostsDto>(post,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	private ResponseEntity<List<PostsDto>>getPostByUser(@PathVariable Long userId){
		List<PostsDto> posts=this.postsService.getPostsByUser(userId);
		return new ResponseEntity<List<PostsDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{cateoryId}")
	private ResponseEntity<List<PostsDto>>getPostByCategory(@PathVariable Long categoryId){
		List<PostsDto> posts=this.postsService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostsDto>>(posts,HttpStatus.OK);
	}
}

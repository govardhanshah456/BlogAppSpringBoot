package com.project.ChatApp.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.ChatApp.payloads.PostResponse;
import com.project.ChatApp.payloads.PostsDto;
import com.project.ChatApp.services.FileUploadService;
import com.project.ChatApp.services.PostsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	private PostsService postsService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/api/{userId}/{categoryId}/create")
	private ResponseEntity<PostsDto>createPost(@RequestBody PostsDto post,@PathVariable Long userId,@PathVariable Long categoryId){
		PostsDto postDto=this.postsService.createPost(post, userId, categoryId);
		return new ResponseEntity<PostsDto>(postDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/api/{postId}/update")
	private ResponseEntity<PostsDto>updatePost(@RequestBody PostsDto post,@PathVariable Long postId){
		PostsDto postDto=this.postsService.updatePost(post, postId);
		return new ResponseEntity<PostsDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/api/{postId}/delete")
	private ResponseEntity<String>deletePost(@PathVariable Long postId){
		this.postsService.deletePost(postId);
		return new ResponseEntity<String>("Deleted Post Successfully!",HttpStatus.OK);
	}
	
	@GetMapping("")
	public PostResponse getAllPosts(
	        @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
	        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
	        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		PostResponse postDto=this.postsService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return postDto;
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
	
	@PostMapping("/api/image/upload/{postId}")
	public ResponseEntity<PostsDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Long postId
	) throws IOException {
		String fileName = this.fileUploadService.uploadImage(path, image);
		PostsDto postDto = this.postsService.getPostById(postId);
		postDto.setImageUrl(fileName);
		PostsDto updatePost = this.postsService.updatePost(postDto, postId);
		return new ResponseEntity<PostsDto> (updatePost,HttpStatus.OK);
	}
	
	@GetMapping("/image/{imageName}")
	public void renderImage(
			@PathVariable("imageName") String fileName,
			HttpServletResponse response
	) throws IOException {
		InputStream resource = this.fileUploadService.getResource(path, fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}

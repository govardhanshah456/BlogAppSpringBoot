package com.project.ChatApp.impl;

import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ChatApp.entities.Category;
import com.project.ChatApp.entities.Posts;
import com.project.ChatApp.entities.User;
import com.project.ChatApp.exceptions.ResourceNotFoundException;
import com.project.ChatApp.payloads.CategoryDto;
import com.project.ChatApp.payloads.PostResponse;
import com.project.ChatApp.payloads.PostsDto;
import com.project.ChatApp.payloads.UserDto;
import com.project.ChatApp.repositories.CategoryRepository;
import com.project.ChatApp.repositories.PostsRepository;
import com.project.ChatApp.repositories.UserRepository;
import com.project.ChatApp.services.PostsService;
@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostsDto createPost(PostsDto postDto, Long userId, long categoryId) {
		// TODO Auto-generated method stub
		CategoryDto category=this.modelMapper.map(this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId)), CategoryDto.class);
		UserDto user=this.modelMapper.map(this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId)), UserDto.class);
		Posts post=this.modelMapper.map(postDto, Posts.class);
		post.setCreatedAt(new Date());
		post.setImageUrl(postDto.getImageUrl());
		post.setCategory(this.modelMapper.map(category, Category.class));
		post.setUser(this.modelMapper.map(user, User.class));
		this.postsRepository.save(post);
		return this.modelMapper.map(post, PostsDto.class);
	}

	@Override
	public PostsDto updatePost(PostsDto postDto, Long postId) {
		Posts post=this.postsRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageUrl(postDto.getImageUrl());
		post.setCategory(this.modelMapper.map(postDto.getCategory(), Category.class));
		return this.modelMapper.map(post, PostsDto.class);
		
	}

	@Override
	public void deletePost(Long postId) {
		// TODO Auto-generated method stub
		Posts post=this.postsRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		this.postsRepository.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=(sortDir=="asc")?Sort.by(sortBy):Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page<Posts>post=this.postsRepository.findAll(p);
		List<Posts>posts=post.getContent();
		List<PostsDto>postDtos=new ArrayList();
		posts.forEach((postt)->{
			postDtos.add(this.modelMapper.map(postt, PostsDto.class));
		});
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(post.getNumber());
		postResponse.setPageSize(post.getSize());
		postResponse.setTotalElements(post.getNumberOfElements());
		postResponse.setTotalPages(post.getTotalPages());
		postResponse.setLastPage(post.isLast());
		return postResponse;
	}

	@Override
	public PostsDto getPostById(Long postId) {
		// TODO Auto-generated method stub
		Posts post=this.postsRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		return this.modelMapper.map(post, PostsDto.class);
	}

	@Override
	public List<PostsDto> getPostsByUser(Long userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		// TODO Auto-generated method stub
		List<Posts>posts=this.postsRepository.findByUser(user);
		List<PostsDto>postDtos=new ArrayList();
		posts.forEach((post)->{
			postDtos.add(this.modelMapper.map(post, PostsDto.class));
		});
		return postDtos;
	}

	@Override
	public List<PostsDto> getPostsByCategory(Long categoryId) {
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
		// TODO Auto-generated method stub
		List<Posts>posts=this.postsRepository.findByCategory(category);
		List<PostsDto>postDtos=new ArrayList();
		posts.forEach((post)->{
			postDtos.add(this.modelMapper.map(post, PostsDto.class));
		});
		return postDtos;
	}

	@Override
	public List<PostsDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}

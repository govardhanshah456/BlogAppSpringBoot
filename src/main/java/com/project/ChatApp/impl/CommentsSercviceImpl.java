package com.project.ChatApp.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ChatApp.entities.Comments;
import com.project.ChatApp.entities.Posts;
import com.project.ChatApp.entities.User;
import com.project.ChatApp.exceptions.ResourceNotFoundException;
import com.project.ChatApp.payloads.CommentsDto;
import com.project.ChatApp.payloads.PostsDto;
import com.project.ChatApp.payloads.UserDto;
import com.project.ChatApp.repositories.CommentsRepository;
import com.project.ChatApp.services.CommentsService;
import com.project.ChatApp.services.PostsService;
import com.project.ChatApp.services.UserService;
@Service
public class CommentsSercviceImpl implements CommentsService{

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentsDto createComment(CommentsDto comment, Long postId, Long userId) {
		// TODO Auto-generated method stub
		PostsDto post=this.postsService.getPostById(postId);
		UserDto user = this.userService.getUserById(userId);
		Comments comment1=this.modelMapper.map(comment,Comments.class);
		comment1.setContent(comment.getContent());
		comment1.setPost(this.modelMapper.map(post, Posts.class));
		comment1.setUser(this.modelMapper.map(user, User.class));
		this.commentsRepository.save(comment1);
		return this.modelMapper.map(comment1, CommentsDto.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		// TODO Auto-generated method stub
		Comments comment=this.commentsRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
		this.commentsRepository.delete(comment);
	}

}

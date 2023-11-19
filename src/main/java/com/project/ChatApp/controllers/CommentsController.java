package com.project.ChatApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ChatApp.payloads.CommentsDto;
import com.project.ChatApp.services.CommentsService;

@RestController
@RequestMapping("")
public class CommentsController {
	
	@Autowired
	private CommentsService commentsService;
	@PostMapping("/api/user/{userId}/post/{postId}/comment/create")
	public ResponseEntity<CommentsDto> createComment(
			@RequestBody CommentsDto comment,
			@PathVariable("postId") Long postId,
			@PathVariable("userId") Long userId
	){
		CommentsDto res= this.commentsService.createComment(comment, postId, userId);
		return new ResponseEntity<CommentsDto>(res,HttpStatus.OK);
	}
	@DeleteMapping("/api/comment/{commentId}")
	public ResponseEntity<String> createComment(
			@PathVariable("commentId") Long commentId
	){
		this.commentsService.deleteComment(commentId);
		return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
	}
}

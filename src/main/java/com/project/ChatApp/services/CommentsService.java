package com.project.ChatApp.services;

import com.project.ChatApp.payloads.CommentsDto;

public interface CommentsService {
	CommentsDto createComment(CommentsDto comment,Long postId,Long userId);
	void deleteComment(Long commentId);
}

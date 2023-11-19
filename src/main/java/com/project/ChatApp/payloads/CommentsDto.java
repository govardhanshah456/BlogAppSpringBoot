package com.project.ChatApp.payloads;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsDto {
	private Long id;
	@Size(max=500,message="Comment Should have at most 500 letters.")
	private String content;
	
	private UserDto user;
}

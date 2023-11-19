package com.project.ChatApp.payloads;

import java.util.ArrayList;
import java.util.List;

import com.project.ChatApp.entities.Category;
import com.project.ChatApp.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostsDto {
	private Long id;
	@NotEmpty
	private String title;
	@Size(min=4,message="Description Should have at least 4 letters.")
	private String content;
	private String imageUrl="default.png";
	private CategoryDto category;
	private UserDto user;
	private List<CommentsDto> comments=new ArrayList<>();
}

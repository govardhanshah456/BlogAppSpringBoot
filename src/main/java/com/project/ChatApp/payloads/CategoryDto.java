package com.project.ChatApp.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
public class CategoryDto {
	private Long id;
	@NotEmpty(message="Name Should Not Be Null!")
	private String name;
	@NotEmpty(message="Description Should Not Be Null!")
	private String description;
}

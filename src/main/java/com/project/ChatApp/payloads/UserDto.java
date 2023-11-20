package com.project.ChatApp.payloads;


import java.util.ArrayList;
import java.util.List;

import com.project.ChatApp.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
    private long id;
    @NotEmpty
    @Size(min=4,message="Username Should Be At Least 4 characters Long")
    private String username;
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    private String about;
    private String password;
    private List<Role> roles = new ArrayList();
}

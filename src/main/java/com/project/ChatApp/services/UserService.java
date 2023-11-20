package com.project.ChatApp.services;

import com.project.ChatApp.payloads.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
	UserDto registerUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
}

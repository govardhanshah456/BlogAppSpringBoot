package com.project.ChatApp.impl;

import com.project.ChatApp.entities.Role;
import com.project.ChatApp.entities.User;
import com.project.ChatApp.exceptions.ResourceNotFoundException;
import com.project.ChatApp.payloads.UserDto;
import com.project.ChatApp.repositories.RoleRepository;
import com.project.ChatApp.repositories.UserRepository;
import com.project.ChatApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto createUser(UserDto user) {
        User userr=new User();
        userr=this.userDtoToUser(user);
        userr.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepo.findById((long) 2).get();
//        userr.getRole().add(role);
        this.userRepo.save(userr);
        return this.userToUserDto(userr);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
        return this.userToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        System.out.println(user.getAuthorities());
        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User>users=userRepo.findAll();
        List<UserDto>userDtos=new ArrayList<>();
        users.forEach((user)->{
            userDtos.add(this.userToUserDto(user));
        });
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepo.delete(user);
    }

    private User userDtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
        return user;
    }

    private UserDto userToUserDto(User user){
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

	@Override
	public UserDto registerUser(UserDto user) {
		// TODO Auto-generated method stub
		User userr = this.modelMapper.map(user, User.class);
		System.out.print(user.getRoles());
		userr.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepo.findById((long) 2).get();
        userr.getRoles().add(role);
        this.userRepo.save(userr);
        return this.userToUserDto(userr);
	}
}

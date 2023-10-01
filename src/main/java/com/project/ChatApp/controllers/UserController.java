package com.project.ChatApp.controllers;

import com.project.ChatApp.entities.User;
import com.project.ChatApp.payloads.UserDto;
import com.project.ChatApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
    	System.out.println(user.toString());
        UserDto newUser= userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto user,@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully!");
    }

}

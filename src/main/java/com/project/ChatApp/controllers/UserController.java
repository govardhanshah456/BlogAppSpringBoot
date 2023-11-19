package com.project.ChatApp.controllers;

import com.project.ChatApp.entities.User;
import com.project.ChatApp.payloads.UserDto;
import com.project.ChatApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
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
    @PutMapping("/api/v1/update/{id}")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto user,@PathVariable("id") Long id){
    	try {
            System.out.print(user);
            return ResponseEntity.ok(userService.updateUser(user, id));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in updateUser method");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully!");
    }

}

package com.project.ChatApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ChatApp.payloads.JwtAuthRequest;
import com.project.ChatApp.payloads.JwtAuthResponse;
import com.project.ChatApp.payloads.UserDto;
import com.project.ChatApp.repositories.RoleRepository;
import com.project.ChatApp.services.UserService;
import com.project.ChatApp.utils.JwtUtil;
import java.lang.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest req) {
		
		System.out.print(req);
		try {
			Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
			System.out.print(authentication);
			if(authentication.isAuthenticated()){
	    	UserDetails user = this.userDetailsService.loadUserByUsername(req.getUsername());
	    	System.out.print(user);
	        String token = this.jwtUtil.generateToken(user.getUsername());
	        JwtAuthResponse res = new JwtAuthResponse();
	        res.setToken(token);
	        return new ResponseEntity(res,HttpStatus.OK);
	    } else {
	    
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Authentication failed: " + e.getMessage());
		    
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		
		try {
			UserDto user = this.userService.registerUser(userDto);
	        return new ResponseEntity(user,HttpStatus.CREATED);
	    }
		
		catch(Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Authentication failed: " + e.getMessage());
		    
		}
	}
}

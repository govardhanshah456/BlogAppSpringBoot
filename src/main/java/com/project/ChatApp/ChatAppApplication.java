package com.project.ChatApp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.project.ChatApp.entities.Role;
import com.project.ChatApp.repositories.RoleRepository;

@SpringBootApplication
@ComponentScan
public class ChatAppApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			Role role1 = new Role();
			role1.setId((long) 1);
			role1.setName("ROLE_ADMIN");
			this.roleRepo.save(role1);
			Role role2 = new Role();
			role2.setId((long) 2);
			role2.setName("ROLE_USER");
			this.roleRepo.save(role2);
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}
	
}

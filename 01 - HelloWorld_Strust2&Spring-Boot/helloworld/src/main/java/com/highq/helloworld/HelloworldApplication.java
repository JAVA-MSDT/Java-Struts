package com.highq.helloworld;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.highq.helloworld.domain.User;
import com.highq.helloworld.model.service.UserService;

@SpringBootApplication
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserService userService) {
		return (args) -> {
			System.out.println("Starting adding Users..... ");
			User userOne = new User("admin", "admin");
			User userTwo = new User("user", "user");

			userService.saveUser(userOne);
			userService.saveUser(userTwo);
		};
	}

}

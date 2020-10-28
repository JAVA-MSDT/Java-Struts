package com.highq.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highq.helloworld.domain.User;
import com.highq.helloworld.model.service.UserService;

@RestController
public class HelloWorldController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}

	@GetMapping(path = "/hello")
	public String getHello() {
		return "Hello world";
	}
}

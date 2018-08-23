package com.shobhit.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shobhit.redis.bean.User;
import com.shobhit.redis.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping (value = "/hello")
	public String sayHello() {
		return "Hello From Redis Application";
	}

	@RequestMapping (value = "/save/{userId}/{userName}/{password}")
	public List<User> saveUser(@PathVariable int userId, @PathVariable String userName, @PathVariable String password) {
		User user = new User(userId, userName, password);
		log.info("-- saveUser -- " + user);

		userRepository.saveUser(user);

		return userRepository.findUsers();
	}

	@RequestMapping (value = "/find")
	public List<User> findUsers() {
		log.info("-- findUsers Controller --");

		return userRepository.findUsers();
	}

	@RequestMapping (value = "/find/{userId}")
	public User findUserById(@PathVariable int userId) {
		log.info("-- findUserById Controller -- " + userId);

		return userRepository.findUserById(userId);
	}

	@RequestMapping (value = "/update/{userId}/{userName}/{password}")
	public List<User> updateUser(@PathVariable int userId, @PathVariable String userName, @PathVariable String password) {
		User user = new User(userId, userName, password);
		log.info("-- updateUser -- " + user);

		userRepository.updateUser(user);

		return userRepository.findUsers();
	}

	@RequestMapping (value = "/delete/{userId}")
	public List<User> deleteUser(@PathVariable int userId) {
		log.info("-- deleteUser Controller -- " + userId);

		userRepository.deleteUser(userId);

		return userRepository.findUsers();
	}
}
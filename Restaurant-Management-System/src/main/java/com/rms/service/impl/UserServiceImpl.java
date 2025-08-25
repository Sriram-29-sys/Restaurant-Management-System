package com.rms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rms.entity.User;
import com.rms.repository.UserRepository;
import com.rms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	// Constructor Injection
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findAll().stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findWithUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
}

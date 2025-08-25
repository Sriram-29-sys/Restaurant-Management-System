package com.rms.service;

import java.util.List;
import java.util.Optional;

import com.rms.entity.User;

public interface UserService {
	User saveUser(User user);

	Optional<User> findWithUsernameAndPassword(String username, String password);

	List<User> getAllUsers();

	Optional<User> findByUsername(String username);
}

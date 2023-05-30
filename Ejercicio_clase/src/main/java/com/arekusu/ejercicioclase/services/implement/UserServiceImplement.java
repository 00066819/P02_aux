package com.arekusu.ejercicioclase.services.implement;

import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.repositories.UserRepository;
import com.arekusu.ejercicioclase.services.UserService;

public class UserServiceImplement implements UserService {
	
	UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	

}

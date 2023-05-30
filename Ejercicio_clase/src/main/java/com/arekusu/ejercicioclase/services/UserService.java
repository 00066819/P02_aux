package com.arekusu.ejercicioclase.services;

import org.springframework.stereotype.Service;

import com.arekusu.ejercicioclase.models.entities.User;

@Service
public interface UserService {
	User findByUsername(String username);

}

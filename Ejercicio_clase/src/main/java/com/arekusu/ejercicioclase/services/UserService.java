package com.arekusu.ejercicioclase.services;

import com.arekusu.ejercicioclase.models.dtos.UserDTO;

import com.arekusu.ejercicioclase.models.entities.User;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserByUsername(String username, String email);
    User createUser(UserDTO userDTO);
}

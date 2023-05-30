package com.arekusu.ejercicioclase.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.arekusu.ejercicioclase.models.entities.User;

@Repository
public interface UserRepository extends ListCrudRepository<User,UUID> {
	
	User findByUsername(String username);

}

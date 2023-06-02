package com.arekusu.ejercicioclase.controllers;

import com.arekusu.ejercicioclase.models.dtos.MessageDTO;
import com.arekusu.ejercicioclase.models.dtos.UserDTO;
import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.services.PlaylistService;
import com.arekusu.ejercicioclase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.arekusu.ejercicioclase.utils.RequestErrorHandler;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    
    @Autowired
    private PlaylistService playlistService;
 

    @Autowired
    public void PlaylistController(PlaylistService playlistService, UserService userService) {
        this.playlistService = playlistService;
        this.userService = userService;
    }
    
    private RequestErrorHandler errorHandler;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username, username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, BindingResult validations) {
    	
    	if(validations.hasErrors()) {
    		return new ResponseEntity<> (
        			errorHandler.mapErrors(validations.getFieldErrors()),HttpStatus.BAD_REQUEST);
    	}    	
        try {
            User createdUser = userService.createUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    
}
    
    @GetMapping("playlist")
    public ResponseEntity<?> getPlaylistByUser(@RequestParam("User")String userData, @RequestParam(required = false) String title){
    	User user = userService.getUserByUsername(userData,title);
    	if(user == null) {
    		return new ResponseEntity<>(
    				new MessageDTO("user not found"),HttpStatus.NOT_FOUND);
    	}
    	List<Playlist> playlists;
        if (title != null && !title.isEmpty()) {
            playlists = playlistService.findPlaylistsByUserAndTitle(user, title);
        } else {
            playlists = playlistService.findPlaylistByUser(user);
        }
        return ResponseEntity.ok(playlists);
    }
 }  


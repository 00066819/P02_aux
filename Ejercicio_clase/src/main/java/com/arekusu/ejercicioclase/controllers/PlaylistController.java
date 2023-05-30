package com.arekusu.ejercicioclase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arekusu.ejercicioclase.models.dtos.MessageDTO;
import com.arekusu.ejercicioclase.models.dtos.SavePlaylistDTO;
import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.services.PlaylistService;
import com.arekusu.ejercicioclase.services.UserService;
import com.arekusu.ejercicioclase.utils.RequestErrorHandler;

import jakarta.validation.Valid;
import net.bytebuddy.build.Plugin.Engine.ErrorHandler;

@RestController
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	private UserService userService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	
	
    @PostMapping("/save")
    public ResponseEntity<String> savePlaylist(@RequestBody SavePlaylistDTO playlistDTO , @RequestParam("username") String username) {
        
    	User user = userService.findByUsername(username);
    	System.out.println(user);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        
        try {
            playlistService.save(playlistDTO, user);
            return ResponseEntity.ok("Playlist guardada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al guardar la playlist");
        }
    }
	
	@DeleteMapping("/deleteByTitle/{title}")
	public ResponseEntity<String> deleteByTitle(@PathVariable String title){
        try {
            playlistService.deleteByTitle(title);
            return ResponseEntity.ok("Playlist eliminada con éxito");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar la playlist");
        }
    }
	
	@GetMapping("/all")
	public ResponseEntity<List<Playlist>> findAllPlaylist() {
	    List<Playlist> playlists = playlistService.findAll();
	    
	    if (playlists==null) {
	        return ResponseEntity.noContent().build();
	    }
	    
	    return ResponseEntity.ok(playlists);
	}
}



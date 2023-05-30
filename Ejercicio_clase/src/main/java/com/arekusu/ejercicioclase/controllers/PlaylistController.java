package com.arekusu.ejercicioclase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arekusu.ejercicioclase.models.dtos.MessageDTO;
import com.arekusu.ejercicioclase.models.dtos.SavePlaylistDTO;
import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.services.PlaylistService;
import com.arekusu.ejercicioclase.utils.RequestErrorHandler;

import jakarta.validation.Valid;
import net.bytebuddy.build.Plugin.Engine.ErrorHandler;

public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	
	
	@PostMapping("/")
	public ResponseEntity<?> savePlaylist(@ModelAttribute @Valid SavePlaylistDTO info, User user , BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			playlistService.save(info);
			return new ResponseEntity<>(
					new MessageDTO("Playlist created"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			
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
}



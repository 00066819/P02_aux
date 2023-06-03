package com.arekusu.ejercicioclase.controllers;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.arekusu.ejercicioclase.models.dtos.SavePlaylistDTO;
import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.Song;
import com.arekusu.ejercicioclase.models.entities.SongXPlaylist;
import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.services.PlaylistService;
import com.arekusu.ejercicioclase.services.SongService;
import com.arekusu.ejercicioclase.services.SongXPlaylistService;
import com.arekusu.ejercicioclase.services.UserService;
import com.arekusu.ejercicioclase.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")

public class PlaylistController {
	
	@Autowired
    private final PlaylistService playlistService;
	@Autowired
    private final SongService songService;
	@Autowired
    private final UserService userService;
	
	@Autowired
    private final SongXPlaylistService songXPlaylistService;
	@Autowired
	private RequestErrorHandler errorHandler;
	
    @Autowired
    public PlaylistController(PlaylistService playlistService, SongService songService, UserService userService, SongXPlaylistService songXPlaylistService) {
        this.playlistService = playlistService;
        this.songService = songService;
        this.userService = userService;
        this.songXPlaylistService = songXPlaylistService;
    }

    @PostMapping("/playlist")
    public ResponseEntity<?> savePlaylist(@RequestBody @Valid SavePlaylistDTO playlistDTO, BindingResult validations) {
    	
    	if(validations.hasErrors()) {
    		return new ResponseEntity<> (
        			errorHandler.mapErrors(validations.getFieldErrors()),HttpStatus.BAD_REQUEST);
    	}  
    	
        User user = userService.getUserByUsername(playlistDTO.getUser().getUsername(),playlistDTO.getUser().getEmail());
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
    public ResponseEntity<String> deleteByTitle(@PathVariable String title) {
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

        if (playlists == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playlists);
    }
    
    @PostMapping("/playlist/{playlistCode}")
    public ResponseEntity<?> addsSongToPlaylist(@PathVariable String playlistCode, @RequestBody String songCode, BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(validations.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        System.out.println(songCode);
        System.out.println(playlistCode);
        
        Song song = songService.searchSongByCode(songCode);
        Playlist playlist = playlistService.searchPlaylistByCode(playlistCode);
        

        if (song != null && playlist != null) {
            try {
                songXPlaylistService.save(new Timestamp(System.currentTimeMillis()), song, playlist);
                return ResponseEntity.ok("Canción agregada a la playlist con éxito");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al agregar la canción a la playlist");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la canción o la playlist");
    }

    
}

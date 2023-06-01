package com.arekusu.ejercicioclase.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arekusu.ejercicioclase.models.dtos.SavePlaylistDTO;
import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.repositories.PlaylistRepository;
import com.arekusu.ejercicioclase.services.PlaylistService;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImplement implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImplement(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(SavePlaylistDTO info, User user) throws Exception {
        Playlist playlist = new Playlist();
        playlist.setTitle(info.getTitle());
        playlist.setDescription(info.getDescription());
        playlist.setUserCode(user.getCode().toString()); 
        playlistRepository.save(playlist);
    }


    @Override
    public void deleteByTitle(String title) throws Exception {
        playlistRepository.deleteByTitle(title);
    }
    @Override
    public Playlist findOneById(String id) {
        UUID playlistId;
        try {
            playlistId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El ID de la playlist no es válido");
        }
        return playlistRepository.findById(playlistId)
                .orElse(null);
    }


    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }
}


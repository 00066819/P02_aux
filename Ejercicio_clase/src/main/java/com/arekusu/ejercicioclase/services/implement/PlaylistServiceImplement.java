package com.arekusu.ejercicioclase.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.arekusu.ejercicioclase.models.dtos.SavePlaylistDTO;
import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.User;
import com.arekusu.ejercicioclase.repositories.PlaylistRepository;
import com.arekusu.ejercicioclase.services.PlaylistService;

import jakarta.transaction.Transactional;


public class PlaylistServiceImplement implements PlaylistService {
	
	@Autowired
	private PlaylistRepository playlistRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SavePlaylistDTO info, User user) throws Exception {
		Playlist playlist = new Playlist();
		        playlist.setTitle(info.getTitle());
		        playlist.setDescription(info.getDescription());
		        playlist.setUserCode(user.getCode());

		        playlistRepository.save(playlist);

	playlistRepository.save(playlist);
		
	}

	@Override
	public void deleteByTitle(String title) throws Exception {
		playlistRepository.deleteByTitle(title);
	}

	@Override
	public Playlist findOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Playlist> findAll() {
		return playlistRepository.findAll();
	}

}

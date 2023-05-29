package com.arekusu.ejercicioclase.services;

import java.util.List;

import com.arekusu.ejercicioclase.models.dtos.SavePlaylistDTO;
import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.User;

public interface PlaylistService {
	void save (SavePlaylistDTO info, User user) throws Exception;
	void deleteById(String id) throws Exception;
	Playlist findOneById(String id);
	List<Playlist> findAll();
	
}

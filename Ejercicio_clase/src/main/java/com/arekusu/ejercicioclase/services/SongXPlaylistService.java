package com.arekusu.ejercicioclase.services;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.arekusu.ejercicioclase.models.entities.Playlist;
import com.arekusu.ejercicioclase.models.entities.Song;
import com.arekusu.ejercicioclase.models.entities.SongXPlaylist;

@Service
public interface SongXPlaylistService {
	void save(Timestamp time, Song song, Playlist playlist);
}
